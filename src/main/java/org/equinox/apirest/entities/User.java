package org.equinox.apirest.entities;

import jakarta.persistence.*;
import org.equinox.apirest.validations.IsRequired;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    @Column(unique = true)
    private String username;

    @IsRequired
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private Set<Role> roles;

    @Transient
    private boolean admin;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username, String password, Boolean isActive) {
        this();
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @PrePersist
    public void prePersist() {
        this.isActive = Boolean.TRUE;
    }
}
