package com.quisofka.quizzes.infrastructure.drivenAdapters.data;


import com.quisofka.quizzes.infrastructure.drivenAdapters.util.validators.LevelEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "students")
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class StudentData {

    @Id
    private String id = UUID.randomUUID().toString().substring(0,10);

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "LastName can't be null")
    @NotBlank(message = "LastName can't be empty")
    private String lastName;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email address")
    @Indexed(unique = true)
    private String email;

    //@NotNull(message = "isAuthorized can't be null")
    //@NotBlank(message = "isAuthorized can't be empty")
    private Boolean isAuthorized;

    @NotNull(message = "level can't be null")
    @NotBlank(message = "level can't be empty")
    @LevelEnum(message="level should be 'Initial', 'Basic' or 'Intermediate'")
    private String level;

    /*public StudentData(String name, String lastName, String email, Boolean isAuthorized, String level) {
        //this.id = UUID.randomUUID().toString().substring(0,10);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.isAuthorized = isAuthorized;
        this.level = level;
    }*/
}
