package com.quisofka.quizzes.infrastructure.drivenAdapters;

import com.quisofka.quizzes.infrastructure.drivenAdapters.data.QuizData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryQuiz extends ReactiveMongoRepository<QuizData, String> {
}
