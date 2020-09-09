package com.webapi.contacts.controller;


import com.webapi.contacts.model.Skill;
import com.webapi.contacts.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    final SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAllSkills();
    }

    @GetMapping(value = "{id}")
    public Skill get(@PathVariable Long id) {
        return skillService.getSkillForId(id);
    }

    @PostMapping
    public Skill create(@RequestBody final Skill skill) {
        return skillService.saveSkill(skill);
    }

    @PutMapping
    public Skill update(@RequestBody Skill skillToUpdate) {
        return skillService.updateSkill(skillToUpdate);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        skillService.deleteSkillForId(id);
    }
}
