package com.webapi.contacts.service;

import com.webapi.contacts.ContactsApplication;
import com.webapi.contacts.model.Contact;
import com.webapi.contacts.model.Skill;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ContactsApplication.class})
@DirtiesContext

public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void getOneContact() {
        //given
        Long idOfContact = 2L; //+ the initial records in db from data.sql
        //when
        Contact result = contactService.getContactForId(idOfContact);
        //then
        assertNotNull(result);
        assertEquals(idOfContact, result.getContactId());

    }

    @Test
    public void getAllContacts() {
        //given the initial records in db from data.sql
        //when
        List<Contact> fetchedContacts = contactService.getAllContacts();
        //then
        assertEquals(4, fetchedContacts.size());
    }

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
    @Disabled
    public void updateContact() {
        //given
        Contact contactToUpdate = contactService.getContactForId(2L);

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

    public static Contact getValidContact() {
        Skill skill1 = new Skill(null, "Java", "Advanced");
       // Skill skill2 = new Skill(null, "Angular", "Advanced");
        Set<Skill> skillList = new HashSet<>();
        skillList.add(skill1);
       // skillList.add(skill2);
        Contact contact = new Contact(null, "Marc", "Johnson", "Route de la Maladière 6, 1022 Chavannes-près-Renens", "marc@johnson.ch", "0585735240");
        contact.setSkills(skillList);
        return contact;
    }


    @Test
    @Disabled
    public void deleteContact() {
        //given
        Long idOfContact = 2L; //+ the initial records in db from data.sql

        //when
        contactService.deleteContactForId(idOfContact);

        //then

        assertThrows(Exception.class, () -> contactService.getContactForId(idOfContact));
    }

}
