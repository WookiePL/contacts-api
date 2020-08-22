package com.webapi.contacts.service;

import com.webapi.contacts.model.Contact;
import com.webapi.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<Contact> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }


    public Contact getContactForId(Long id) {

        return contactRepository.getOne(id);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public Contact updateContact(Contact contactToUpdate) {
        Contact existingContact = contactRepository.findById(contactToUpdate.getContactId()).orElse(null);
        return contactRepository.save(contactToUpdate);
    }
}
