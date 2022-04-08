package com.mic.microservice.service;

import com.mic.microservice.controller.AddResponse;
import com.mic.microservice.data.Country;
import com.mic.microservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepo;
    public  Country addCountry(Country country){
        countryRepo.save(country);
        return country;
    }
    public List<Country> getAllCounty(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Country> pagedResult = countryRepo.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Country>();
        }
        //return countryRepo.findAll();
    }
    public Country getCountryById(int id){
        List<Country> countries = countryRepo.findAll();
        Country country = null;
        for (Country count : countries){
            if(count.getId() == id)
                country = count;
        }
        return country;
    }
    public Country getCountryByName(String countryName){
        List<Country> countries = countryRepo.findAll();
        Country country = null;
        for(Country count: countries){
            if(count.getCountryName().equalsIgnoreCase(countryName))
                country = count;
        }
        return country;
    }
    public Country updateCountry(Country country){
        countryRepo.save(country);
        return country;
    }

    public void deleteCountry(Country country){
             countryRepo.delete(country);
    }
}
