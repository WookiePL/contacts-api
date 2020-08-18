package com.webapi.contacts.controller;

import com.webapi.contacts.model.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @GetMapping
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1L, "Lukasz", "Janas", "Rue de la Mèbre 9, 1020 Renens", "lukasz@janas.com", "0585736750"));
        contacts.add(new Contact(2L, "Marc", "Johnson", "Route de la Maladière 6, 1022 Chavannes-près-Renens", "marc@johnson.ch", "0585735240"));
        return contacts;
    }

}
