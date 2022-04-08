package com.mic.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String countryName;
    private String countryCapital;


}
