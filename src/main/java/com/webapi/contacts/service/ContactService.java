package com.webapi.contacts.service;

import com.webapi.contacts.exception.InaccessibleContactException;
import com.webapi.contacts.model.Contact;
import com.webapi.contacts.model.User;
import com.webapi.contacts.repository.ContactRepository;
import com.webapi.contacts.service.auth.CustomUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public Contact updateContact(Contact contactToUpdate) {
        Contact contact = contactRepository.findById(contactToUpdate.getContactId()).orElseThrow(EntityNotFoundException::new);
        User userFromContactToUpdate = contact.getUser();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User userFromContext = ((CustomUserPrincipal) authentication.getPrincipal()).getUser();
        //if (currentUserName.equals(userFromRequest.getUsername())) {
        if (userFromContext.equals(userFromContactToUpdate)) {
            return contactRepository.save(contactToUpdate);
        } else {
            throw new InaccessibleContactException();
        }
    }

    public void deleteContactForId(Long id) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        contactRepository.delete(existingContact);
    }


}
