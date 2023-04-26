package com.quisofka.quizzes.domain.model.quiz.gateways;


import com.quisofka.quizzes.domain.model.quiz.Quiz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuizRepositoryGateway {

    Flux<Quiz> getAllQuizzes();
    Mono<Quiz> getQuizById(String id);
    Mono<Quiz> createQuiz(Quiz quiz);
    Mono<Void> deleteAll();
}
