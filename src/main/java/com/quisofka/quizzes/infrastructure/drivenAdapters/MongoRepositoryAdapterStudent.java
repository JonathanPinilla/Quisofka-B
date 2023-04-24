package com.quisofka.quizzes.infrastructure.drivenAdapters;

import com.quisofka.quizzes.domain.model.student.Student;
import com.quisofka.quizzes.domain.model.student.gateways.StudentRepository;
import com.quisofka.quizzes.infrastructure.drivenAdapters.data.StudentData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterStudent implements StudentRepository {

    private final MongoDBRepositoryStudent repository;

    private final ObjectMapper mapper;


    @Override
    public Mono<Student> getStudentById(String studentId) {
        return this.repository.findById(studentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "student with id: " + studentId)))
                .map(studentData -> mapper.map(studentData, Student.class));
    }

    @Override
    public Mono<Student> getStudentByEmail(String email) {
        return this.repository.findByEmail(email)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                "student with email: " + email)))
                .map(studentData -> mapper.map(studentData, Student.class));
    }

    @Override
    public Mono<Student> saveStudent(Student student) {
        return this.repository
                .save(mapper.map(student, StudentData.class))
                .switchIfEmpty(Mono.empty())
                .map(studentData -> mapper.map(studentData, Student.class));
    }

    @Override
    public Mono<Student> updateStudent(String studentId, Student student) {
        return this.repository
                .findById(studentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "student with id: " + studentId)))
                .flatMap(studentData -> {
                    student.setId(studentData.getId());
                    return repository.save(mapper.map(student, StudentData.class));
                })
                .map(studentData -> mapper.map(studentData, Student.class));
    }

    @Override
    public Mono<Void> deleteStudent(String studentId) {
        return this.repository
                .findById(studentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "student with id: " + studentId)))
                .flatMap(studentData -> this.repository.deleteById(studentData.getId()));
    }
}
