package com.webapi.contacts.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    private String name;
    private String level;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skillId, skill.skillId) &&
                Objects.equals(name, skill.name) &&
                Objects.equals(level, skill.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, name, level);
    }
}
