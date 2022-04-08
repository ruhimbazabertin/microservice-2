package com.mic.microservice.controller;

import com.mic.microservice.data.Country;
import com.mic.microservice.service.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {CountryControllerTest.class})
class CountryControllerTest {
    @Mock
    CountryService countryService;
    @InjectMocks
    CountryController countryController;
    List<Country> mycountries;
    Country country;

    @Test
    @Order(1)
    void createCountry() {
        //Given
        country = new Country(1,"USA","Washington");
        when(countryService.addCountry(country)).thenReturn(country);
         ResponseEntity<Country> returnedResponse = countryController.createCountry(country);
         assertEquals(HttpStatus.CREATED, returnedResponse.getStatusCode());
         assertEquals(country, returnedResponse.getBody());

    }

    @Test
    @Order(2)
    void getAllCountry() {
        //Given
//       mycountries = new ArrayList<Country>();
//        mycountries.add(new Country(1,"India","Delhi"));
//        mycountries.add(new Country(2,"kenya","Nairobi"));
//        mycountries.add(new Country(3,"Rwanda","Kigali"));
//        when(countryService.getAllCounty()).thenReturn(mycountries);
//        ResponseEntity<List<Country>> returnedResponse = countryController.getAllCountry();
//        assertEquals(HttpStatus.FOUND, returnedResponse.getStatusCode());
//        assertEquals(3,returnedResponse.getBody().size());
    }

    @Test
    @Order(3)
    void getCountry() {
        country = new Country(2,"USA","Washington");
        int countryId = 2;
        when(countryService.getCountryById(countryId)).thenReturn(country);
        ResponseEntity<Country> returnedResponse = countryController.getCountry(countryId);
        assertEquals(2,returnedResponse.getBody().getId());
        assertEquals(HttpStatus.FOUND, returnedResponse.getStatusCode());
    }

    @Test
    void testGetCountry() {
        country = new Country(2,"USA","Washington");
        String countryName = "USA";
        when(countryService.getCountryByName(countryName)).thenReturn(country);
        ResponseEntity<Country> returnedResponse = countryController.getCountry(countryName);
        assertEquals(HttpStatus.FOUND, returnedResponse.getStatusCode());
        assertEquals(countryName, returnedResponse.getBody().getCountryName());
    }

    @Test
    void updateCountry() {
        //Given
        Country country = new Country(1,"USA","Washington");
        int countryId = 1;
        when( countryService.getCountryById(countryId));
        when(countryService.updateCountry(country));
        ResponseEntity<Country> returnedResponse = countryController.updateCountry(1,country);
        //assertEquals(HttpStatus.OK, returnedResponse.getStatusCode());
        assertEquals(1, returnedResponse.getBody().getId());
        assertEquals("USA",returnedResponse.getBody().getCountryName());
        assertEquals("Washington", returnedResponse.getBody().getCountryCapital());
    }

    @Test
    void deleteCountry() {
        //Given
        Country country = new Country(1,"USA","Washington");
        int countryId = 1;
        when(countryService.getCountryById(countryId)).thenReturn(country);
        ResponseEntity<Country> returnedResponse = countryController.deleteCountry(countryId);
        assertEquals(HttpStatus.OK, returnedResponse.getStatusCode());
    }
}