package com.webapi.contacts.controller;


import com.webapi.contacts.model.Skill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @GetMapping
    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1L, "Java", "Experienced"));
        skills.add(new Skill(2L, "Javascript", "Experienced"));
        return skills;
    }
}
