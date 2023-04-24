package com.quisofka.quizzes.domain.usecase.student.savestudent;

import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.domain.model.student.gateways.StudentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveStudent implements Function<Student, Mono<Student>> {

    private final StudentRepository studentRepository;

    @Override
    public Mono<Student> apply(Student student) {
        return studentRepository.saveStudent(student);
    }
}
