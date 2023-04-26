package com.quisofka.quizzes.domain.model.quiz;

import com.quisofka.quizzes.domain.model.question.Question;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Quiz {

    private String id;
    private Map<String, Boolean> questions;
    private HashSet<Question> questionList;
    private Double score;
    private String studentId;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private String status;
    private String level;

}
