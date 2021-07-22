package com.example.springdatatest.repository;

import com.example.springdatatest.dao.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Long> {
}
