package com.quisofka.quizzes.infrastructure.entryPoints;

import com.quisofka.quizzes.domain.model.quiz.Quiz;
import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.domain.usecase.quiz.createQuiz.CreateQuizUseCase;
import com.quisofka.quizzes.domain.usecase.quiz.deleteAll.DeleteAllQuizzesUseCase;
import com.quisofka.quizzes.domain.usecase.quiz.getAllQuizzes.GetAllQuizzesUseCase;
import com.quisofka.quizzes.domain.usecase.quiz.getQuizById.GetQuizByIdUseCase;
import com.quisofka.quizzes.domain.usecase.student.savestudent.SaveStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class RouterRestQuiz {


    @Bean
    public RouterFunction<ServerResponse> getAll(GetAllQuizzesUseCase getAllQuizzesUseCase){
        return route(GET("/quisofka/quizzes/quizzes"),
                request -> ServerResponse.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllQuizzesUseCase.get(), Quiz.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }


    @Bean
    public RouterFunction<ServerResponse> getQuestionById(GetQuizByIdUseCase getQuizByIdUseCase){
        return route(GET("/quisofka/quizzes/{id}"),
                request -> getQuizByIdUseCase.apply(request.pathVariable("id"))
                        .switchIfEmpty(Mono.error(new Throwable(HttpStatus.NO_CONTENT.toString())))
                        .flatMap(question -> ServerResponse.status(200)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(question))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }




    @Bean
    public RouterFunction<ServerResponse> createQuiz (CreateQuizUseCase createQuizUseCase){
        return route(POST("/quisofka/quizzes/quizzes").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Quiz.class)
                        .flatMap(quiz -> createQuizUseCase.apply(quiz)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .bodyValue(throwable.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAllQuizzes(DeleteAllQuizzesUseCase deleteAllQuizzesUseCase){
        return route(DELETE("/quisofka/quizzes/quizzes"),
                request -> deleteAllQuizzesUseCase.get()
                        .thenReturn(
                                ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(Collections.singletonMap("message", "All quizzes have been deleted")))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

}
