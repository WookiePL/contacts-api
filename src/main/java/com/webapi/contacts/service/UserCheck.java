package com.webapi.contacts.service;

import com.webapi.contacts.model.Contact;
import com.webapi.contacts.model.Skill;
import com.webapi.contacts.model.User;
import com.webapi.contacts.service.auth.CustomUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCheck {
    boolean checkIfUserHasRightsToSkill(Skill skill) {
        List<User> usersFromContactToUpdate = skill.getContacts().stream()
                .map(Contact::getUser)
                .distinct()
                .collect(Collectors.toList());
        User userFromContext = getUserFromContext();

        return usersFromContactToUpdate.contains(userFromContext);
    }

    boolean checkIfUserHasRightsToContact(Contact contact) {
        User userFromContactToChange = contact.getUser();
        User userFromContext = getUserFromContext();

        return true;
    }

    User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUserPrincipal) authentication.getPrincipal()).getUser();
    }

}
