package com.mic.microservice.service;

import com.mic.microservice.data.Country;
import com.mic.microservice.repository.CountryRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CountryServiceTest.class)
class CountryServiceTest {
    @Mock
    CountryRepository countryrepo;
    @InjectMocks
    CountryService countryService;
    //public List<Country> myCountries;
    @Test
    @Order(4)
    void addCountry() {
        //Given
        Country country = new Country(4,"Uganda","Kampala");
        when(countryrepo.save(country)).thenReturn(country);//mocking
        Country returnedCountry = countryService.addCountry(country);
        assertEquals(country,returnedCountry);
    }

    @Test
    @Order(1)
    @Disabled
    void getAllCounty() {
        //Given
//        List<Country> myCountries = new ArrayList<Country>();
//        myCountries.add(new Country(1,"India","Delhi"));
//        myCountries.add(new Country(2,"kenya","Nairobi"));
//        when(countryrepo.findAll()).thenReturn(myCountries);//Mocking
//        int size = countryService.getAllCounty().size();
//        assertEquals(2,size);
    }

    @Test
    @Order(2)
    void getCountryById() {
        //Given
        List<Country> myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1,"India","Delhi"));
        myCountries.add(new Country(2,"kenya","Nairobi"));
        myCountries.add(new Country(3,"Rwanda","Kigali"));
        int id = 3;
        when(countryrepo.findAll()).thenReturn(myCountries);//Mocking
       Country returnedCountry = countryService.getCountryById(id);
       int countryId = returnedCountry.getId();
       assertEquals(id,countryId);
    }

    @Test
    @Order(3)
    void getCountryByName() {
        //Given
        List<Country> myCountries = new ArrayList<Country>();
        myCountries.add(new Country(1,"India","Delhi"));
        myCountries.add(new Country(2,"kenya","Nairobi"));
        myCountries.add(new Country(3,"Rwanda","Kigali"));
        when(countryrepo.findAll()).thenReturn(myCountries);//Mocking
            String countryName="Rwanda";
        Country returnedCountry = countryService.getCountryByName(countryName);
        String name = returnedCountry.getCountryName();
        assertEquals("Rwanda",name);
    }

    @Test
    @Order(5)
    void updateCountry() {
        //Given
        Country country = new Country(4,"Tanzania","Dalsalamu");
        when(countryrepo.save(country)).thenReturn(country);//mocking
        Country returnedCountry = countryService.updateCountry(country);
        assertEquals(country,returnedCountry);
    }

    @Test
    @Order(6)
    void deleteCountry() {
        //Given
        Country country = new Country(4,"Tanzania","Dalsalamu");
         countryService.deleteCountry(country);
         verify(countryrepo,times(1)).delete(country);//mocking for the method without return.
    }
}