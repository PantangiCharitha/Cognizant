package com.cognizant.orm_learn.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private int id;

    private String name;

    private double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_code")
    private Country country;

    @ManyToMany
@JoinTable(
    name = "employee_skill",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id")
)
private List<Skill> skills;
    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
}