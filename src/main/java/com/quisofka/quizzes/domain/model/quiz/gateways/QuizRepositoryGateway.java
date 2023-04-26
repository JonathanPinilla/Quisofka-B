package com.quisofka.quizzes.domain.model.quiz.gateways;


import com.quisofka.quizzes.domain.model.quiz.Quiz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuizRepositoryGateway {

    Flux<Quiz> getAllQuizzes();
    Mono<Quiz> getQuizById(String id);
    // Hacerlo para generalizaod?
    Mono<Quiz> createQuiz(Quiz quiz);
    Mono<Quiz> createFirstLvlQuiz(Quiz quiz);
    Mono<Quiz> createSecondLvlQuiz(Quiz quiz);
    Mono<Quiz> createThirdLvlQuiz(Quiz quiz);
    Mono<Void> deleteAll();
}
