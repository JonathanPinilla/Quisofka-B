package com.quisofka.quizzes.infrastructure.drivenAdapters.data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "students")
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {

    @Id
    private String id;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email address")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "isAuthorized can't be null")
    @NotBlank(message = "isAuthorized can't be empty")
    private Boolean isAuthorized;

    @NotNull(message = "level can't be null")
    @NotBlank(message = "level can't be empty")
    private enum level {pending, initial, basic, intermediate;}
}
