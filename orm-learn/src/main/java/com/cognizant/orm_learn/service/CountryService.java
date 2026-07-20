package com.cognizant.orm_learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;

import jakarta.transaction.Transactional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country getCountry(String code) {
        return countryRepository.findById(code).orElse(null);
    }
        @Transactional
public void addCountry(Country country) {
    countryRepository.save(country);

    }
    @Transactional
public Country getCountryWithEmployees(String code) {
    return countryRepository.findById(code).orElse(null);
}
@Transactional
public List<Country> getAllCountriesHQL() {
    return countryRepository.getAllCountriesHQL();
}
@Transactional
public Country getCountryByCodeHQL(String code) {
    return countryRepository.getCountryByCodeHQL(code);
}@Transactional
public long getCountryCount() {
    return countryRepository.getCountryCount();
}
@Transactional
public Country getCountryWithEmployeesHQL(String code) {
    return countryRepository.getCountryWithEmployeesHQL(code);
}
@Transactional
public List<Country> getAllCountriesNative() {
    return countryRepository.getAllCountriesNative();
}


}