package com.quisofka.quizzes.infrastructure.drivenAdapters;

import com.quisofka.quizzes.domain.model.quiz.Quiz;
import com.quisofka.quizzes.domain.model.quiz.enums.Level;
import com.quisofka.quizzes.domain.model.quiz.enums.Status;
import com.quisofka.quizzes.domain.model.quiz.gateways.QuizRepositoryGateway;
import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.infrastructure.drivenAdapters.data.QuizData;
import com.quisofka.quizzes.infrastructure.drivenAdapters.data.StudentData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterQuiz implements QuizRepositoryGateway {

    private final MongoDBRepositoryQuiz quizRepository;
    private final ObjectMapper mapper;


    @Override
    public Flux<Quiz> getAllQuizzes() {
        return this.quizRepository
        .findAll()
                .switchIfEmpty(Mono.error(new Throwable("No quizzes available")))
                .map(item -> mapper.map(item, Quiz.class));
    }

    @Override
    public Mono<Quiz> getQuizById(String id) {
        return this.quizRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "quiz with id: " + id)))
                .map(quizData -> mapper.map(quizData, Quiz.class));
    }

    @Override
    public Mono<Quiz> createQuiz(Quiz quiz) {
        return Mono.just(quiz)
                .flatMap(quiz1 -> {
                    Quiz quiz2 = Quiz.builder()
                            //.questions(quiz1.getQuestions())
                            .studentId(quiz1.getStudentId())
                            .status(Status.GENERATED.name())
                            .build();
                    return this.quizRepository.save(mapper.map(quiz2, QuizData.class));

                }).map(quiz3 -> mapper.map(quiz3, Quiz.class));
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.quizRepository.deleteAll()
                .onErrorResume(Mono::error);
    }
}
