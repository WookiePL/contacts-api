package com.webapi.contacts.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "skills",
        joinColumns = {@JoinColumn( name = "contact_id")},
        inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills;

    public Contact() {
    }

    public Contact(Long contactId, String firstname, String lastname, String address, String email, String phoneNumber) {
        this.contactId = contactId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) &&
                Objects.equals(firstname, contact.firstname) &&
                Objects.equals(lastname, contact.lastname) &&
                Objects.equals(address, contact.address) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(skills, contact.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstname, lastname, address, email, phoneNumber, skills);
    }
}
