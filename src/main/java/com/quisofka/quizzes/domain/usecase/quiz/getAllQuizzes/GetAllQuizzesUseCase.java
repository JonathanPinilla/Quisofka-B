package com.quisofka.quizzes.domain.usecase.quiz.getAllQuizzes;

import com.quisofka.quizzes.domain.model.quiz.Quiz;
import com.quisofka.quizzes.domain.model.quiz.gateways.QuizRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllQuizzesUseCase implements Supplier<Flux<Quiz>> {

    private final QuizRepositoryGateway repositoryGateway;

    @Override
    public Flux<Quiz> get() {
        return repositoryGateway.getAllQuizzes();
    }
}
