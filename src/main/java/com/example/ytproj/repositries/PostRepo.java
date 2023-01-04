
package com.example.ytproj.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

import com.example.ytproj.entities.Catrgory;
import com.example.ytproj.entities.Post;
import com.example.ytproj.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByU(User u);

    List<Post> findByCt(Catrgory ct);

    List<Post> findByTitleContaining(String keyword);
    
    
   
}
