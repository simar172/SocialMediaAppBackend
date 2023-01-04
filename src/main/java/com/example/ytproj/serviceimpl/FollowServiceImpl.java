package com.example.ytproj.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.Follow;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.repositries.FollowRepo;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.service.FolowService;

@Service
public class FollowServiceImpl implements FolowService {
    @Autowired
    FollowRepo fr;
    @Autowired
    Repo r;
    @Autowired
    ModelMapper mm;

    @Override
    public void followUser(int cuid, int uid) {
        // TODO Auto-generated method stub
        User cuser = r.findById(cuid).get();
        User user = r.findById(uid).get();

        Optional<Follow> findFollowByFollowerIdAndFollowingId = fr.findFollowByFollowerIdAndFollowingId(cuser.getId(),
                user.getId());
        if (findFollowByFollowerIdAndFollowingId.isPresent()) {

            fr.delete(findFollowByFollowerIdAndFollowingId.get());
            return;
        }
        Follow f = new Follow();
        f.setFollower(cuser);
        f.setFollowing(user);
        fr.save(f);
    }

    @Override
    public void folowBack(int cuid, int uid) {
        // TODO Auto-generated method stub
        User cuser = r.findById(cuid).get();
        User user = r.findById(uid).get();
        Follow f = new Follow();
        f.setFollower(cuser);
        f.setFollowing(user);

    }

    @Override
    public List<UserDto> getUserFollowers(int uid) {
        // TODO Auto-generated method stub
        List<UserDto> followers = new ArrayList<>();
        List<Follow> findAllByFollowingId = fr.findAllByFollowingId(uid);
        for (Follow f : findAllByFollowingId) {
            UserDto map = mm.map(r.findById(f.getFollower().getId()).get(), UserDto.class);
            followers.add(map);
        }
        return followers;
    }

    @Override
    public List<UserDto> getUserFollowing(int uid) {
        // TODO Auto-generated method stub
        List<Follow> findAllByFollowingId = fr.findAllByFollowerId(uid);
        List<UserDto> following = new ArrayList<UserDto>();
        for (Follow f : findAllByFollowingId) {
            following.add(mm.map(f.getFollowing(), UserDto.class));
        }
        return following;
    }

    @Override
    public boolean isFollowing(int uid, int profileId) {
        // TODO Auto-generated method stub
        Optional<Follow> findFollowByFollowerIdAndFollowingId = fr.findFollowByFollowerIdAndFollowingId(uid, profileId);
        if (findFollowByFollowerIdAndFollowingId.isPresent())
            return true;
        return false;
    }

}
