
package com.example.ytproj.service;

import com.example.ytproj.entities.User;
import com.example.ytproj.payload.UserDto;
import java.util.*;

public interface Service {

    UserDto registerUser(UserDto u);

    UserDto createuser(UserDto u);

    UserDto updateuser(UserDto u, int id);

    UserDto getuserbyid(int id);

    List<UserDto> getall();

    void deleteuser(int id);
}
