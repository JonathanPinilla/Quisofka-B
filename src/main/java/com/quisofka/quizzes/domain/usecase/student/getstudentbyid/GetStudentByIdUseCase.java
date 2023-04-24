package com.quisofka.quizzes.domain.usecase.student.getstudentbyid;

import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.domain.model.student.gateways.StudentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetStudentByIdUseCase implements Function<String, Mono<Student>> {

    private final StudentRepository studentRepository;

    @Override
    public Mono<Student> apply(String studentId) {
        return studentRepository.getStudentById(studentId);
    }
}
