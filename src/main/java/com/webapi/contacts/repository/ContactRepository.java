package com.webapi.contacts.repository;

import com.webapi.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByUserUsernameContains(final String username);
}
