package com.tmsapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {
    private String loginName;
    private String password;
}
