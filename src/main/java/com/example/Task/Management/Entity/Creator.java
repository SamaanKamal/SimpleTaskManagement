package com.example.Task.Management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Creator")
public class Creator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "email")
    private String email;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "self")
    private boolean self;

    public Creator() {
    }

    public Creator(String email, String displayName, boolean self) {
        this.email = email;
        this.displayName = displayName;
        this.self = self;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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
