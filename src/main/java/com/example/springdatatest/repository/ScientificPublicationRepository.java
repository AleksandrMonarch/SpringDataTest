package com.example.springdatatest.repository;

import com.example.springdatatest.dao.ScientificPublication;
import org.springframework.data.repository.CrudRepository;

public interface ScientificPublicationRepository extends CrudRepository<ScientificPublication, String> {
}
