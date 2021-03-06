package com.webapi.contacts.controller;

import com.webapi.contacts.model.Contact;
import com.webapi.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RequestMapping("/contacts")
public class ContactsController {

    final ContactService contactService;

    @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAll() {
        return contactService.getAllContacts();
    }

    @GetMapping(value = "{id}")
    public Contact get(@PathVariable Long id) {
        return contactService.getContactForId(id);
    }

    @PostMapping
    public Contact create(@RequestBody final Contact contact) {
        return contactService.saveContact(contact);
    }

    @PutMapping
    public Contact update(@RequestBody Contact contactToUpdate) {
        return contactService.updateContact(contactToUpdate);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        contactService.deleteContactForId(id);
    }
}
