package com.tmsapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class User implements Serializable {
    private int id;
    private String loginName;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String registrationDate = LocalDate.now().toString();

    public User(String loginName, String password, String name, String surname, String email, String phoneNumber, String dateOfBirth, String registrationDate) {
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }
}
