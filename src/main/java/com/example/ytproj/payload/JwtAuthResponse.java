package com.example.ytproj.payload;

import lombok.Data;

@Data
public class JwtAuthResponse {
    String token;
    UserDto u;
}
