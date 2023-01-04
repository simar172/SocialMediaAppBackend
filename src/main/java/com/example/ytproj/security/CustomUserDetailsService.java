
package com.example.ytproj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.User;
import com.example.ytproj.repositries.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.User;
import com.example.ytproj.repositries.Repo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    Repo r;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = r.findByEmail(username);
//        System.out.println("------------------------------------" + user.getName() + "");
        if (user == null) {
            return null;
        }
        return user;
    }

}
