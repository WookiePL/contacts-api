package com.webapi.contacts.service;

import com.webapi.contacts.exception.UnchangeableSkillException;
import com.webapi.contacts.model.Skill;
import com.webapi.contacts.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final UserCheck userCheck;

    @Autowired
    public SkillService(SkillRepository skillRepository, UserCheck userCheck) {
        this.skillRepository = skillRepository;
        this.userCheck = userCheck;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillForId(Long id) {
        return skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.saveAndFlush(skill);
    }

    public Skill updateSkill(Skill skillToUpdate) {
        Skill existingSkill = skillRepository.findById(skillToUpdate.getSkillId()).orElseThrow(EntityNotFoundException::new);

        if (userCheck.checkIfUserHasRightsToSkill(existingSkill)) {
            return skillRepository.save(skillToUpdate);
        } else {
            throw new UnchangeableSkillException();
        }
    }

    public void deleteSkillForId(Long id) {
        Skill existingSkill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (userCheck.checkIfUserHasRightsToSkill(existingSkill)) {
            skillRepository.delete(existingSkill);
        } else {
            throw new UnchangeableSkillException();
        }
    }
}
