package com.softuni.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String conformPassword;
    private String email;
    private String git;

    public UserRegisterBindingModel() {
    }

    @NotNull
    @Length(min = 2, message = "Username must be minimum two characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull
    @Length(min = 3, message = "Password must be minimum three characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getConformPassword() {
        return conformPassword;
    }

    public void setConformPassword(String conformPassword) {
        this.conformPassword = conformPassword;
    }

    @NotNull
    @Email(message = "Enter valid mail address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+",message = "Enter valid git profile.")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
