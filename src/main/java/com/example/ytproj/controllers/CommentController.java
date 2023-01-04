package com.example.ytproj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ytproj.payload.CommentDto;
import com.example.ytproj.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService cs;

    @PostMapping("/create/{uid}/{pid}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto cr, @PathVariable int pid,@PathVariable int uid) {
        CommentDto createComment = cs.createComment(cr, pid,uid);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cid}")
    public void deleteComment(@PathVariable int cid) {
        cs.deleteComment(cid);
    }

}