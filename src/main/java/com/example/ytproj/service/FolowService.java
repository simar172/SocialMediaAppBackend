package com.example.ytproj.service;

import java.util.List;

import com.example.ytproj.entities.Follow;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.UserDto;

public interface FolowService {
    public void followUser(int cuid, int uid);

    public void folowBack(int cuid, int uid);

    public List<UserDto> getUserFollowers(int uid);

    public List<UserDto> getUserFollowing(int uid);

    boolean isFollowing(int uid, int profileId);
}
