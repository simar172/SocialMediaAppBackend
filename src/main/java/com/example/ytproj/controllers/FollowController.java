package com.example.ytproj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ytproj.entities.Follow;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.serviceimpl.FollowServiceImpl;

@RestController
@RequestMapping("/flw")
public class FollowController {
    @Autowired
    FollowServiceImpl fs;

    @PostMapping("/conf/{cuid}/{uid}")
    public void followUser(@PathVariable int cuid, @PathVariable int uid) {
        fs.followUser(cuid, uid);
    }

    @PostMapping("/fbk/{cuid}/{uid}")
    public void followBack(@PathVariable int cuid, @PathVariable int uid) {
        fs.folowBack(cuid, uid);
    }

    @GetMapping("/user/flwrs/{uid}")
    public List<UserDto> getUserFollowers(@PathVariable int uid) {
        List<UserDto> userFollowers = fs.getUserFollowers(uid);
        return userFollowers;
    }

    @GetMapping("/user/flwng/{uid}")
    public List<UserDto> getUserFollowing(@PathVariable int uid) {
        List<UserDto> userFollowing = fs.getUserFollowing(uid);
        return userFollowing;
    }

    @GetMapping("/user/isflwng/{uid}/{profileUid}")
    public ResponseEntity<?> isFollowing(@PathVariable int uid, @PathVariable int profileUid) {
        boolean following = fs.isFollowing(uid, profileUid);
        return new ResponseEntity(following, HttpStatus.OK);
    }

}
