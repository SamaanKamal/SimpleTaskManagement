package com.example.Task.Management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Organizer")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id")
    private Integer organizerId;

    @Column(name = "email")
    private String email;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "self")
    private boolean self;

    public Organizer() {
    }

    public Organizer(String email, String displayName, boolean self) {
        this.email = email;
        this.displayName = displayName;
        this.self = self;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }
}
