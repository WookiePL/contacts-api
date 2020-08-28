package com.webapi.contacts.service;

import com.webapi.contacts.ContactsApplication;
import com.webapi.contacts.model.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ContactsApplication.class})
@DirtiesContext
public class SkillServiceTest {

    @Autowired
    SkillService skillService;
    
    @Test
    public void getOneSkill() {
        //given
        Long idOfSkill = 2L; //+ the initial records in db from data.sql
        //when
        Skill result = skillService.getSkillForId(idOfSkill);
        //then
        assertNotNull(result);
        assertEquals(idOfSkill, result.getSkillId());

    }

    @Test
    public void getAllSkills() {
        //given the initial records in db from data.sql
        //when
        List<Skill> fetchedSkills = skillService.getAllSkills();
        //then
        assertEquals(5, fetchedSkills.size());
    }
}
