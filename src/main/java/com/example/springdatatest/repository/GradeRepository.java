package com.example.springdatatest.repository;

import com.example.springdatatest.dao.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, String> {
}
