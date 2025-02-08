package com.flowcent.authservice.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
    private String phoneNumber;

}
