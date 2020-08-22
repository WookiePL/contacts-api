package com.webapi.contacts.service;

import com.webapi.contacts.ContactsApplication;
import com.webapi.contacts.model.Contact;
import com.webapi.contacts.model.Skill;
import com.webapi.contacts.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ContactsApplication.class})
@DirtiesContext
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void saveContact() {
        //given
        Contact newContactToSave = getValidContact();
        //when
        Contact result = contactService.saveContact(newContactToSave);
        //then
        assertNotNull(result.getContactId());
        assertEquals("Marc", result.getFirstname());
        assertEquals("Johnson", result.getLastname());
        assertEquals("Route de la Maladière 6, 1022 Chavannes-près-Renens", result.getAddress());
        assertEquals("marc@johnson.ch", result.getEmail());
        assertEquals("0585735240", result.getPhoneNumber());
    }

    @Test
    public void updateContact() {
        //given
        Contact contactToUpdate = contactService.saveContact(getValidContact());
        //contactToUpdate.setContactId(2L);
        Long idBeforeUpdate = contactToUpdate.getContactId();
        //when
        Contact result = contactService.updateContact(contactToUpdate);
        //then
        assertEquals(idBeforeUpdate, result.getContactId());
        assertEquals("Marc", result.getFirstname());
        assertEquals("Johnson", result.getLastname());
        assertEquals("Route de la Maladière 6, 1022 Chavannes-près-Renens", result.getAddress());
        assertEquals("marc@johnson.ch", result.getEmail());
        assertEquals("0585735240", result.getPhoneNumber());
    }

    private Contact getValidContact() {
        Skill skill1 = new Skill(null, "Java", "Advanced");
       // Skill skill2 = new Skill(null, "Angular", "Advanced");
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill1);
       // skillList.add(skill2);
        Contact contact = new Contact(null, "Marc", "Johnson", "Route de la Maladière 6, 1022 Chavannes-près-Renens", "marc@johnson.ch", "0585735240");
        contact.setSkills(skillList);
        return contact;
    }

}
