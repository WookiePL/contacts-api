package com.webapi.contacts.service;

import com.webapi.contacts.model.Skill;
import com.webapi.contacts.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }


    public List<Skill> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills;
    }


    public Skill getSkillForId(Long id) {

        return skillRepository.getOne(id);
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.saveAndFlush(skill);
    }

    public Skill updateSkill(Skill skillToUpdate) {
        skillRepository.findById(skillToUpdate.getSkillId()).orElseThrow(EntityNotFoundException::new);
        return skillRepository.save(skillToUpdate);
    }

    public void deleteSkillForId(Long id) {
        Skill existingSkill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        skillRepository.delete(existingSkill);
    }
}
