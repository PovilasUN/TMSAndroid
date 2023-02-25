package com.tmsapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateRequest {
    private int id;
    private String name;
    private String surname;
    private String loginName;
    private String password;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String driverLicenceNumber;
}
