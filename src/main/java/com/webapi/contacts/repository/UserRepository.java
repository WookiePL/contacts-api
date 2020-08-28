package com.webapi.contacts.repository;

import com.webapi.contacts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(final String username);
}
