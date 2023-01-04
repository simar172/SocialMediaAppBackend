package com.example.ytproj.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    String username;
    String password;
}
