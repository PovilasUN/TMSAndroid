package com.tmsapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Driver extends User {
    @SerializedName("driverLicenceNumber")
    private String driverLicenceNumber;

    public Driver(String loginName, String password, String name, String surname, String email, String phoneNumber, String dateOfBirth, String registrationDate, String driverLicenceNumber) {
        super(loginName, password, name, surname, email, phoneNumber, dateOfBirth, registrationDate);
        this.driverLicenceNumber = driverLicenceNumber;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " " + getEmail() + " " + driverLicenceNumber;
    }
}


