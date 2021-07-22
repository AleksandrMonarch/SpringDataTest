package com.example.springdatatest.repository;

import com.example.springdatatest.dao.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, String> {

    @Query("SELECT g FROM Grade g JOIN FETCH g.students s")
    List<Grade> getGradesAndStudents();
}
