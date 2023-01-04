package com.example.ytproj.service;

import java.util.List;

import com.example.ytproj.entities.Likes;
import com.example.ytproj.entities.User;

public interface LikeService {

    long getLikesOfPost(int pid);

    Likes addLike(Likes l);

    List<User> getUsersOfLikedPost(String pid);
}
