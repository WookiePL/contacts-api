package com.webapi.contacts.service;

import com.webapi.contacts.exception.UnchangableContactException;
import com.webapi.contacts.model.Contact;
import com.webapi.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserCheck userCheck;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserCheck userCheck) {
        this.contactRepository = contactRepository;
        this.userCheck = userCheck;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactForId(Long id) {
        return contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public Contact updateContact(Contact contactToUpdate) {
        Contact contact = contactRepository.findById(contactToUpdate.getContactId()).orElseThrow(EntityNotFoundException::new);

        if (userCheck.checkIfUserHasRightsToContact(contact)) {
            return contactRepository.save(contactToUpdate);
        } else {
            throw new UnchangableContactException();
        }
    }

    public void deleteContactForId(Long id) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (userCheck.checkIfUserHasRightsToContact(existingContact)) {
            contactRepository.delete(existingContact);
        } else {
            throw new UnchangableContactException();
        }
    }
}
