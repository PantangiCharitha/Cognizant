package com.cognizant.orm_learn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.service.CountryService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.service.SkillService;


@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static SkillService skillService;

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(OrmLearnApplication.class, args);

        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        skillService = context.getBean(SkillService.class);

        testManyToMany();
    }

    private static void testGetAllCountries() {

        LOGGER.info("Start");

        List<Country> countries = countryService.getAllCountries();

        LOGGER.debug("Countries = {}", countries);

        LOGGER.info("End");
    }

    private static void testGetCountry() {

        LOGGER.info("Start");

        Country country = countryService.getCountry("IN");

        LOGGER.debug("Country = {}", country);

        LOGGER.info("End");
    }

    private static void testAddCountry() {

        LOGGER.info("Start");

        Country country = new Country();

        country.setCode("JP");
        country.setName("Japan");

        countryService.addCountry(country);

        Country newCountry = countryService.getCountry("JP");

        LOGGER.debug("Added Country = {}", newCountry);

        LOGGER.info("End");
    }

    private static void testGetAllEmployees() {

        LOGGER.info("Start Employee Test");

        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {

            LOGGER.info("Employee Name : {}", employee.getName());
            LOGGER.info("Salary        : {}", employee.getSalary());
            LOGGER.info("Country       : {}", employee.getCountry().getName());

            LOGGER.info("------------------------------");
        }

        LOGGER.info("End Employee Test");
    }

    private static void testOneToMany() {

        LOGGER.info("========== OneToMany Demo ==========");

        Country country = countryService.getCountryWithEmployees("IN");

        LOGGER.info("Country : {}", country.getName());

        LOGGER.info("Employees:");

        for (Employee employee : country.getEmployees()) {

            LOGGER.info(employee.getName());

        }

        LOGGER.info("========== End ==========");
    }
    private static void testManyToMany() {

    LOGGER.info("========== ManyToMany Demo ==========");

    Skill skill = skillService.getSkill(2);

    LOGGER.info("Skill : {}", skill.getName());

    LOGGER.info("Employees having this skill:");

    for (Employee employee : skill.getEmployees()) {
        LOGGER.info(employee.getName());
    }

    LOGGER.info("========== End ==========");
}
}