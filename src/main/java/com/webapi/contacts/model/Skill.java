package com.webapi.contacts.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    private String name;
    private String level;

    @ManyToMany( mappedBy = "skills")
    List<Contact> contacts = new ArrayList<>();

    public Skill() {
    }

    public Skill(Long skillId, String name, String level) {
        this.skillId = skillId;
        this.name = name;
        this.level = level;

    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Skill skill = (Skill) o;

        return EqualsBuilder.reflectionEquals(this, skill, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}
