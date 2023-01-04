package com.example.ytproj.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    List<PostDto> content;
    int pagenumber;
    int pagesize;
    int totalpage;
    long totalelements;
    boolean lastpage;
}