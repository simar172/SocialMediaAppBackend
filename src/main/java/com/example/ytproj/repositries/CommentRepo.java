
package com.example.ytproj.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ytproj.entities.Comment;
//import com.example.ytproj.payload.CommentDto;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
