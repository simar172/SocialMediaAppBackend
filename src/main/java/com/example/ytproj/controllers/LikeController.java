package com.example.ytproj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ytproj.entities.Likes;
import com.example.ytproj.entities.User;
import com.example.ytproj.serviceimpl.LikeServiceImpl;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    LikeServiceImpl ls;

    @PutMapping("/{uid}/{pid}")
    @Transactional
    public ResponseEntity<Likes> addLikes(@PathVariable int pid, @PathVariable int uid) {
        Likes addLike = ls.addLike(new Likes(0, uid, pid));
        return new ResponseEntity<Likes>(addLike, HttpStatus.OK);
    }

    @GetMapping("/{pid}")
    @Transactional
    public long getLikes(@PathVariable int pid) {
        long likesOfPost = ls.getLikesOfPost(pid);
        return likesOfPost;
    }

    @GetMapping("check/{pid}/{uid}")
    @Transactional
    public ResponseEntity<Boolean> isLiked(@PathVariable int pid, @PathVariable int uid) {
        boolean liked = ls.isLiked(pid, uid);
        return new ResponseEntity<Boolean>(liked, HttpStatus.OK);
    }

    @GetMapping("/likedUser/{pid}")
    @Transactional
    public ResponseEntity<List<User>> getUsersOfLikedPost(@PathVariable String pid) {
        List<User> usersOfLikedPost = ls.getUsersOfLikedPost(pid);
        return new ResponseEntity<List<User>>(usersOfLikedPost, HttpStatus.OK);
    }
}
