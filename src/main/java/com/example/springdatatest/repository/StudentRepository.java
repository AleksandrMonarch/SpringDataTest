package com.example.springdatatest.repository;

import com.example.springdatatest.dao.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}
