
package com.example.ytproj.service;

import com.example.ytproj.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto ct, int pid, int uid);

    void deleteComment(int cid);
}
