package com.quisofka.quizzes.application.config;

import com.quisofka.quizzes.domain.model.quiz.gateways.QuizRepositoryGateway;
import com.quisofka.quizzes.domain.usecase.quiz.createQuiz.CreateQuizUseCase;
import com.quisofka.quizzes.domain.usecase.quiz.getAllQuizzes.GetAllQuizzesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.quisofka.quizzes.domain.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {


}
