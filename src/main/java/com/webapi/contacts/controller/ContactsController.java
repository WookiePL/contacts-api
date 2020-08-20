package com.webapi.contacts.controller;

import com.webapi.contacts.model.Contact;
import com.webapi.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private ContactRepository contactRepository;

    @Autowired
    public ContactsController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

}
