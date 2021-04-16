package com.softuni.model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;
    private List<Comment> comment;
    private Set<Homework> homeworkSet;

    public User() {
    }

    @OneToMany
    public List<Comment> getComment() {
        return comment;
    }

    public User setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Homework> getHomeworkSet() {
        return homeworkSet;
    }

    public User setHomeworkSet(Set<Homework> homeworkSet) {
        this.homeworkSet = homeworkSet;
        return this;
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "git", nullable = false)
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
