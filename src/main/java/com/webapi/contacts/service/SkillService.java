package com.webapi.contacts.service;

import com.webapi.contacts.exception.UnchangableSkillException;
import com.webapi.contacts.model.Skill;
import com.webapi.contacts.model.User;
import com.webapi.contacts.repository.SkillRepository;
import com.webapi.contacts.service.auth.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.saveAndFlush(skill);
    }

    public Skill updateSkill(Skill skillToUpdate) {
        Skill existingSkill = skillRepository.findById(skillToUpdate.getSkillId()).orElseThrow(EntityNotFoundException::new);
        User userFromContactToUpdate = existingSkill.getContacts().get(0).getUser();
        User userFromContext = getUserFromContext();

        if (userFromContext.equals(userFromContactToUpdate)) {
            return skillRepository.save(skillToUpdate);
        } else {
            throw new UnchangableSkillException();
        }
    }

    public void deleteSkillForId(Long id) {
        Skill existingSkill = skillRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        User userFromContactToUpdate = existingSkill.getContacts().get(0).getUser();
        User userFromContext = getUserFromContext();

        if (userFromContext.equals(userFromContactToUpdate)) {
            skillRepository.delete(existingSkill);
        } else {
            throw new UnchangableSkillException();
        }
    }

    private User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUserPrincipal) authentication.getPrincipal()).getUser();
    }
}
