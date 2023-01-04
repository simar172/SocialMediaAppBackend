
package com.example.ytproj.service;

import java.util.*;

import com.example.ytproj.entities.Post;
import com.example.ytproj.payload.PostDto;
import com.example.ytproj.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto pt, int uid, int cid);

    PostDto updatePost(PostDto pt, int id, int cid);

    void deletePost(int id);

    PostDto getPost(int id);

    PostResponse getAllPost(int ps, int pn, String sort, String dir,int uid);

    List<PostDto> getPostByCategory(int cid);

    List<PostDto> getAllPostByUser(int uid);

    List<PostDto> searchPost(String key);

}
