package com.cognizant.orm_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.repository.SkillRepository;

import jakarta.transaction.Transactional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill getSkill(int id) {
        return skillRepository.findById(id).orElse(null);
    }
}
