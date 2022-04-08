package com.mic.microservice.repository;


import com.mic.microservice.data.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
