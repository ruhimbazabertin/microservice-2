package com.mic.microservice.controller;

import com.mic.microservice.data.Country;
import com.mic.microservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    @Autowired
    CountryService countryService;

    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {

        try {
            country = countryService.addCountry(country);
            return new ResponseEntity<Country>(country, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Country>> getAllCountry(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue ="4") Integer pageSize,
                @RequestParam(defaultValue = "id") String sortBy
                ) {
        try {
            List<Country> countries = countryService.getAllCounty(pageNo,pageSize,sortBy);
            return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") int id) {
        try {
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countryName")
    public ResponseEntity<Country> getCountry(@RequestParam("name") String countryName) {
        try {
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") int id, @RequestBody Country country) {

        try {
            Country existCountry = countryService.getCountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            Country updatedCountry = countryService.updateCountry(existCountry);
            return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
        Country existCountry = null;
        try {
            existCountry = countryService.getCountryById(id);
            countryService.deleteCountry(existCountry);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(existCountry,HttpStatus.OK);
    }
}
