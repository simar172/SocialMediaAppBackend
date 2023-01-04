package com.example.ytproj.payload;

import com.example.ytproj.entities.User;

import lombok.Data;

@Data
public class FollowDto {
    int id;
    User follow;
    User Following;
}
