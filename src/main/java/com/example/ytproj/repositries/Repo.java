
package com.example.ytproj.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ytproj.entities.User;

@Repository
public interface Repo extends JpaRepository<User, Integer> {
    
    User findByEmail(String email);

}
