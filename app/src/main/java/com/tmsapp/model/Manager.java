package com.tmsapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Manager extends User {
    private String companyName;

    public Manager(String loginName, String password, String name, String surname, String email, String phoneNumber, String dateOfBirth, String registrationDate, String companyName) {
        super(loginName, password, name, surname, email, phoneNumber, dateOfBirth, registrationDate);
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " " + getEmail() + " " + companyName;
    }
}
