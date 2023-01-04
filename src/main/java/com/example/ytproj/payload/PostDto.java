package com.example.ytproj.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;

import com.example.ytproj.entities.Catrgory;
import com.example.ytproj.entities.Comment;
import com.example.ytproj.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    int id;
    String title;
    String content;
    String imagename;
    Date date;

    CategoryDto ct;

    UserDto u;

    List<CommentDto> li = new ArrayList<>();
    
}