package com.quisofka.quizzes.domain.model.student.gateways;

import com.quisofka.quizzes.domain.model.student.Student;
import reactor.core.publisher.Mono;

public interface StudentRepository {

    Mono<Student> getStudentById(Student studentId);

    Mono<Student> getStudentByEmail(String email);

    Mono<Student> saveStudent(Student student);

    Mono<Student> updateStudent(String studentId, Student student);

    Mono<Void> deleteStudent(Student studentId);
}
