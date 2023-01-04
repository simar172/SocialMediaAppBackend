
package com.example.ytproj.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.Comment;
import com.example.ytproj.entities.Post;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.CommentDto;
import com.example.ytproj.payload.PostDto;
import com.example.ytproj.repositries.CommentRepo;
import com.example.ytproj.repositries.PostRepo;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.service.CommentService;
import com.example.ytproj.service.PostService;

@Service
public class CommetServiceImpl implements CommentService {
    @Autowired
    PostRepo ps;
    @Autowired
    Repo u;

    @Autowired
    CommentRepo cr;

    @Autowired
    ModelMapper mm;

    @Override
    public CommentDto createComment(CommentDto ct, int pid, int uid) {
        // TODO Auto-generated method stub
        Post p = ps.findById(pid).get();
        Comment nct = mm.map(ct, Comment.class);
        nct.setP(p);
        User user = u.findById(uid).get();
        nct.setU(user);
        cr.save(nct);
        CommentDto ncdto = mm.map(nct, CommentDto.class);

        return ncdto;
    }

    @Override
    public void deleteComment(int cid) {
        // TODO Auto-generated method stub
        cr.deleteById(cid);
    }

}
