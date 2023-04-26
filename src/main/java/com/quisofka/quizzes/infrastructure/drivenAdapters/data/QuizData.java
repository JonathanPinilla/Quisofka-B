package com.quisofka.quizzes.infrastructure.drivenAdapters.data;

import com.quisofka.quizzes.domain.model.question.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "quizzes")
@NoArgsConstructor
@AllArgsConstructor
public class QuizData {

    private String id;
    private Map<Question, Boolean> questions;
    private Double score;
    @NotBlank(message="studentId is required")
    @NotNull(message ="studentId is required")
    private String studentId;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private String status;
    private String level;

}
