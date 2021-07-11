package com.example.springdatatest.repository;

import com.example.springdatatest.dao.StudentGroup;
import org.springframework.data.repository.CrudRepository;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, String> {
}
