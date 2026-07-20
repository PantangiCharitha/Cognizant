package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.cognizant.orm_learn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findByNameContaining(String text);

    List<Country> findByNameStartingWith(String text);

    List<Country> findAllByOrderByNameAsc();
    @Query("FROM Country")
List<Country> getAllCountriesHQL();
@Query("FROM Country c WHERE c.code = :code")
Country getCountryByCodeHQL(@Param("code") String code);
@Query("SELECT COUNT(c) FROM Country c")
long getCountryCount();
@Query("SELECT c FROM Country c JOIN FETCH c.employees WHERE c.code = :code")
Country getCountryWithEmployeesHQL(@Param("code") String code);
@Query(value = "SELECT * FROM country", nativeQuery = true)
List<Country> getAllCountriesNative();

}