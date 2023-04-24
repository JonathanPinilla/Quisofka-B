package com.quisofka.quizzes.domain.model.student;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student {

    private String id;
    private String name;
    private String email;
    private String isAuthorized;
    private enum level {pending, initial, basic, intermediate;}
}
