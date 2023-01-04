package com.example.ytproj.controllers;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ytproj.entities.User;
import com.example.ytproj.payload.JwtAuthRequest;
import com.example.ytproj.payload.JwtAuthResponse;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.security.JwtTokenHelper;
import com.example.ytproj.service.Service;
import com.example.ytproj.serviceimpl.EmailServiceImpl;

@RestController
@RequestMapping("/jwt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    JwtTokenHelper jth;
    @Autowired
    UserDetailsService uds;
    @Autowired
    AuthenticationManager am;
    @Autowired
    Service s;

    @Autowired
    ModelMapper mm;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jar) {
        authenticate(jar.getUsername(), jar.getPassword());
        EmailServiceImpl uc = new EmailServiceImpl();
        UserDetails uname = uds.loadUserByUsername(jar.getUsername());
        String token = jth.generateToken(uname);
        JwtAuthResponse res = new JwtAuthResponse();
        res.setToken(token);
        res.setU(mm.map((User) uname, UserDto.class));
        

        return new ResponseEntity<JwtAuthResponse>(res, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        // TODO Auto-generated method stub
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
        am.authenticate(upat);

    }

    @PostMapping("/newUser")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto udt) {
        UserDto nu = s.registerUser(udt);
        return new ResponseEntity<UserDto>(nu, HttpStatus.CREATED);
    }
}
