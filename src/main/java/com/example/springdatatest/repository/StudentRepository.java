package com.example.springdatatest.repository;

import com.example.springdatatest.dao.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByFirstNameEquals(String firstName);

    List<Student> findStudentsBySecondNameEquals(String secondName);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.firstName = :firstName WHERE s.id = :id")
    Integer updateStudentByFirstName(String firstName, Long id);
}
