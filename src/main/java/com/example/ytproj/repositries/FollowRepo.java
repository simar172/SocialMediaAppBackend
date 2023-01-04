package com.example.ytproj.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ytproj.entities.Follow;
import com.example.ytproj.entities.User;

public interface FollowRepo extends JpaRepository<Follow, Integer> {
    Optional<Follow> findFollowByFollowerIdAndFollowingId(int followerId, int followingId);

    List<Follow> findAllByFollowingId(int uid);

    List<Follow> findAllByFollowerId(int followerId);

}
