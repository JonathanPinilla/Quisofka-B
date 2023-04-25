package com.quisofka.quizzes.domain.usecase.student.getstudentbyemail;

import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.domain.model.student.gateways.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetStudentByEmailUseCaseTest {

    @Mock
    StudentRepository repository;

    GetStudentByEmailUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetStudentByEmailUseCase(repository);
    }

    @Test
    @DisplayName("GetStudentByEmailUseCase_Success")
    void getStudentByEmail(){
        var student = new Student("1", "Diego", "Sanchez",
                "di@gmail.com",true,"initial");

        Mockito.when(repository.getStudentByEmail("di@gmail.com")).thenReturn(Mono.just(student));

        var result = useCase.apply("di@gmail.com");

        StepVerifier.create(result)
                .expectNext(student)
                .expectComplete()
                .verify();

        Mockito.verify(repository, Mockito.times(1)).getStudentByEmail("di@gmail.com");
    }

    @Test
    @DisplayName("GetStudentByEmailUseCase_Failed")
    void getStudentByEmail_Failed() {
        Mockito.when(repository.getStudentByEmail(Mockito.any(String.class)))
                .thenReturn(Mono.error(new Throwable(Integer.toString(
                        HttpURLConnection.HTTP_NOT_FOUND))));

        var result = useCase.apply("di@gmail.com");

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable != null &&
                        throwable.getMessage().equals(Integer.toString(
                                HttpURLConnection.HTTP_NOT_FOUND)))
                .verify();

        Mockito.verify(repository, Mockito.times(1))
                .getStudentByEmail("di@gmail.com");
    }
}