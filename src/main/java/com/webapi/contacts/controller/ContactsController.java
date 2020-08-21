package com.webapi.contacts.controller;

import com.webapi.contacts.model.Contact;
import com.webapi.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @RequestMapping("{id}")
    public Contact get(@PathVariable Long id) {

        return contactRepository.getOne(id);
    }

    @PostMapping
    public Contact create(@RequestBody final Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @PutMapping
    public Contact update(@RequestBody Contact contactToUpdate) {
        Contact existingContact = contactRepository.findById(contactToUpdate.getContactId()).orElse(null);
        return contactRepository.saveAndFlush(contactToUpdate);
    }
}
