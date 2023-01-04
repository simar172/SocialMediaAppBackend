
package com.example.ytproj.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.Catrgory;
import com.example.ytproj.entities.Follow;
import com.example.ytproj.entities.Post;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.PostDto;
import com.example.ytproj.payload.PostResponse;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.repositries.Categoryrepo;
import com.example.ytproj.repositries.FollowRepo;
import com.example.ytproj.repositries.PostRepo;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.service.PostService;

@Service
public class PostServiceimpl implements PostService {
    @Autowired
    PostRepo ps;
    @Autowired
    ModelMapper mm;
    @Autowired
    Categoryrepo cr;
    @Autowired
    Repo rs;
    @Autowired
    FollowRepo fr;

    @Override
    public PostDto createPost(PostDto pt, int uid, int cid) {
        // TODO Auto-generated method stub

        User u = rs.findById(uid).get();
        Catrgory ct = cr.findById(cid).get();

        Post np = mm.map(pt, Post.class);
        np.setImagename(pt.getImagename());
        np.setDate(new Date());
        np.setU(u);
        np.setCt(ct);

        Post save = ps.save(np);
        return mm.map(save, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto pt, int id, int cid) {
        // TODO Auto-generated method stub]
        Post op = ps.findById(id).get();
        Catrgory category = cr.findById(cid).get();
        op.setContent(pt.getContent());
        op.setImagename(pt.getImagename());
        op.setTitle(pt.getTitle());
        op.setCt(category);
        Post np = ps.save(op);
        return mm.map(np, PostDto.class);
    }

    @Override
    public void deletePost(int id) {
        // TODO Auto-generated method stub
        ps.deleteById(id);
    }

    @Override
    public PostDto getPost(int id) {
        // TODO Auto-generated method stub
        Post p = ps.findById(id).get();
        return mm.map(p, PostDto.class);

    }

    @Override
    public PostResponse getAllPost(int pagesize, int pagenumber, String sort, String dir, int uid) {
        // TODO Auto-generated method stub
        Sort sort2 = (dir.equals("asc")) ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        PageRequest pq = PageRequest.of(pagenumber, pagesize, sort2);
        Page<Post> li = ps.findAll(pq);
        List<PostDto> nli = new ArrayList<>();
        li.forEach(i -> nli.add(mm.map(i, PostDto.class)));
        if (uid != 0) {
            List<Follow> findAllByFollowingId = fr.findAllByFollowingId(uid);
            List<PostDto> finalList = new ArrayList<>();
            for (int i = 0; i < nli.size(); i++) {
                PostDto p = nli.get(i);
                if (p.getU().getisPrivate() == false && !finalList.contains(p)) {
                    finalList.add(p);
                }
                if (p.getU().getId() == uid && !finalList.contains(p)) {
                    finalList.add(p);
                }
            }
            for (Follow f : findAllByFollowingId) {
                UserDto u = mm.map(f.getFollower(), UserDto.class);
                List<Post> findByU = ps.findByU(mm.map(u, User.class));
                for (Post p : findByU) {
                    PostDto post = mm.map(p, PostDto.class);
                    if (!finalList.contains(post)) {
                        finalList.add(post);
                    }
                }
            }
//            finalList.remove(finalList.size() - 1);

//            List<PostDto> fli = new ArrayList<>();
//
//            for (Follow f : findAllByFollowingId) {
//
//                List<Post> findByU = ps.findByU(f.getFollower());
//                findByU.forEach(i -> {
//                    fli.add(mm.map(i, PostDto.class));
//                });
//            }
//            List<PostDto> allPostByUser = getAllPostByUser(uid);
//
////            for (int i = 0; i < allPostByUser.size(); i++) {
////                if (fli.get(i).getId() != allPostByUser.get(i).getId()
////                        && allPostByUser.get(i).getU().getId() == uid) {
////                    fli.add(allPostByUser.get(i));
////                }
////
////            }
////            for (int i = 0; i < nli.size(); i++) {
////                if (!nli.get(i).getU().getisPrivate() && !fli.contains(nli.get(i))) {
////                    fli.add(nli.get(i));
////                }
////            }
            PostResponse pr = new PostResponse();
            pr.setContent(finalList);
            pr.setPagenumber(li.getNumber());
            pr.setPagesize(li.getSize());
            pr.setTotalelements(li.getTotalElements());
            pr.setTotalpage(li.getTotalPages());
            pr.setLastpage(li.isLast());
            return pr;
        } else if (uid == 0) {
            List<PostDto> publicPost = new ArrayList<>();
            for (PostDto p : nli) {
                if (p.getU().getisPrivate() == false) {
                    publicPost.add(p);
                }
            }
            PostResponse pr = new PostResponse();

            pr.setContent(publicPost);
            pr.setPagenumber(publicPost.size() - 1);
            pr.setPagesize(publicPost.size());
            pr.setTotalelements(publicPost.size());
            return pr;
        }
        return null;
    }

    public List<PostDto> getPostByCategory(int cid) {
        // TODO Auto-generated method stub
        Catrgory ct = cr.findById(cid).get();
        List<Post> li = ps.findByCt(ct);
        List<PostDto> nli = new ArrayList<PostDto>();
        li.forEach(i -> nli.add(mm.map(i, PostDto.class)));
        return nli;
    }

    @Override
    public List<PostDto> getAllPostByUser(int uid) {
        // TODO Auto-generated method stub
        User u = rs.findById(uid).get();
        List<Post> li = ps.findByU(u);
        List<PostDto> nli = new ArrayList<>();
        li.forEach(i -> nli.add(mm.map(i, PostDto.class)));
        return nli;
    }

    @Override
    public List<PostDto> searchPost(String key) {
        // TODO Auto-generated method stub
        List<Post> li = ps.findByTitleContaining(key);
        List<PostDto> nli = new ArrayList<>();
        li.forEach(i -> nli.add(mm.map(i, PostDto.class)));
        return nli;
    }

}
