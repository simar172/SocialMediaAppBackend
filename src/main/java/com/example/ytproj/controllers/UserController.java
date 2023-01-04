package com.example.ytproj.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ytproj.entities.User;
import com.example.ytproj.payload.PostDto;
import com.example.ytproj.payload.UserDto;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.service.Service;
import com.example.ytproj.upload.ImageServiceImpl;

import io.jsonwebtoken.lang.Collections;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    ModelMapper mm;
    @Autowired
    Service s;
    @Autowired
    ImageServiceImpl ig;
    @Autowired
    com.example.ytproj.serviceimpl.EmailServiceImpl em;
    @Autowired
    Repo rep;
    @Autowired
    BCryptPasswordEncoder bs;

    Random r = new Random(1000);
    static String passChange = "";
    static String rcheck = "";

    @PostMapping("/")
    public ResponseEntity<UserDto> createuser(@Valid @RequestBody UserDto ut) {
        UserDto nut = s.createuser(ut);
        return new ResponseEntity<UserDto>(nut, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto ud, @PathVariable("id") int id) {
        UserDto nut = s.updateuser(ud, id);
        return ResponseEntity.ok(nut);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteuser(@PathVariable("id") int id) {
        s.deleteuser(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getallusers() {
        List<UserDto> li = s.getall();
        return ResponseEntity.ok(li);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getuserbyid(@PathVariable int id) {
        UserDto u = s.getuserbyid(id);
        return ResponseEntity.ok(u);
    }

    @PutMapping(value = "/profile/image/{uid}/{uname}", consumes = "multipart/form-data")
    public ResponseEntity<UserDto> storeProfileImage(@RequestParam("image") MultipartFile mf, @PathVariable int uid,
            @PathVariable("uname") String uname)
            throws IOException {
        String imageName = ig.profileImage(mf, uname);
        UserDto ut = s.getuserbyid(uid);
        ut.setImagename(imageName);
        System.out.println(ut.getImagename());
        UserDto nut = s.updateuser(ut, uid);
        return new ResponseEntity<UserDto>(nut, HttpStatus.OK);
    }

    @GetMapping(value = "/profile/image/{imageName}/{uname}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void showProfileImage(@PathVariable String imageName, @PathVariable("uname") String uname,
            HttpServletResponse hr) throws IOException {
        try {
            InputStream is = ig.serveProfileImage(imageName, uname);
            hr.setContentType(MediaType.IMAGE_JPEG_VALUE);
            org.hibernate.engine.jdbc.StreamUtils.copy(is, hr.getOutputStream());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    @PostMapping("/forgot/{uname}")
    public HttpStatus forgotPassword(@PathVariable String uname) throws MessagingException {

        List<Integer> numbers = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            numbers.add(j);
        }

        java.util.Collections.shuffle(numbers);

        String result = "";
        for (int k = 0; k < 4; k++) {
            result += numbers.get(k).toString();
        }
        rcheck = result;
        em.sendMail(
                "<b> <h5>This is a mail reagrding  reseting your account password.The OTP is given below:</h5> </b> <h4>"
                        + result + " </h4>",
                "NoReply", uname, "simarp804@gmail.com");
        return HttpStatus.OK;
    }

    @PostMapping("/checkOTP/")
    public HttpStatus verifyOtp(@RequestBody String Otp) throws Exception {

        passChange = Otp;
        if (Otp.equals(rcheck)) {
            return HttpStatus.OK;
        }
        throw new Exception("Error");
    }

    @PutMapping("/userPass/{uname}")
    public ResponseEntity<UserDto> passChange(@RequestBody String pass,
            @PathVariable String uname) throws Exception {
        if (!passChange.equals(rcheck)) {
            throw new Exception("Otp not matched");
        }
        User u = rep.findByEmail(uname);
        System.out.println(pass);
        u.setPassword(

                this.bs.encode(pass));
        rep.save(u);
        return new ResponseEntity<UserDto>(mm.map(u, UserDto.class), HttpStatus.CREATED);
    }

//    @PutMapping(value = "/profile/testimage/{uid}", consumes = "multipart/form-data")
//    public ResponseEntity<UserDto> testimage(@RequestParam("image") MultipartFile mf, @PathVariable int uid,
//            String name)
//            throws IOException {
//        String imageName = ig.profileImage(mf);
//        UserDto ut = s.getuserbyid(uid);
//        ut.setImagename(imageName);
//        System.out.println(ut.getImagename());
//        UserDto nut = s.updateuser(ut, uid);
//        return new ResponseEntity<UserDto>(nut, HttpStatus.OK);
//    }
}