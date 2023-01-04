
package com.example.ytproj.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ytproj.config.Constants;
import com.example.ytproj.entities.Role;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.repositries.Role_repo;
import com.example.ytproj.service.Service;

@org.springframework.stereotype.Service
public class Serviceimpl implements Service {
    @Autowired
    Repo rs;
    @Autowired
    ModelMapper mm;
    @Autowired
    Role_repo rlp;

    @Autowired
    BCryptPasswordEncoder ped;

    @Override
    public UserDto createuser(UserDto ud) {
        // TODO Auto-generated method stub

        User u = dtotouser(ud);

        rs.save(u);

        return usertodto(u);
    }

    @Override
    public UserDto updateuser(UserDto u, int id) {
        // TODO Auto-generated method stub
        User ud = rs.findById(id).get();
        ud.setAbout(u.getAbout());
        ud.setEmail(u.getEmail());
        ud.setId(u.getId());
        ud.setName(u.getName());
        ud.setPassword(u.getPassword());
        ud.setImagename(u.getImagename());
        User usertodto = rs.save(ud);
        UserDto usertodto2 = usertodto(usertodto);
        return usertodto2;
    }

    @Override
    public UserDto getuserbyid(int id) {
        // TODO Auto-generated method stub
        User u = rs.findById(id).get();

        return usertodto(u);
    }

    @Override
    public List<UserDto> getall() {
        // TODO Auto-generated method stub
        List<User> li = (List<User>) rs.findAll();
        List<UserDto> ld = new ArrayList<UserDto>();
        li.forEach(i -> ld.add(mm.map(i, UserDto.class)));

        return ld;
    }

    @Override
    public void deleteuser(int id) {
        // TODO Auto-generated method stub
        rs.deleteById(id);

    }

    public User dtotouser(UserDto ud) {
        User u = mm.map(ud, User.class);
        return u;
    }

    public UserDto usertodto(User u) {
        UserDto ud = mm.map(u, UserDto.class);

        return ud;
    }

    @Override
    public UserDto registerUser(UserDto u) {
        // TODO Auto-generated method stub
        User u1 = mm.map(u, User.class);
        u1.setPassword(ped.encode(u1.getPassword()));
        Role role = rlp.findById(Constants.normal_Id).get();
        u1.getSet().add(role);
        User user = rs.save(u1);
        return mm.map(user, UserDto.class);
    }

}
