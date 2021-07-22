package com.example.springdatatest.repository;

import com.example.springdatatest.dao.HomeAddress;
import org.springframework.data.repository.CrudRepository;

public interface HomeAddressRepository extends CrudRepository<HomeAddress, Long> {
}
