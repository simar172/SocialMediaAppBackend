package com.example.ytproj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.ytproj.payload.CategoryDto;
import com.example.ytproj.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService s;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createUser(@RequestBody CategoryDto ct) {
        CategoryDto u = s.createuser(ct);
        return new ResponseEntity(u, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateUser(@RequestBody CategoryDto cd, int id) {
        CategoryDto ud = s.updateteuser(cd, id);
        return new ResponseEntity(ud, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        s.deletebyid(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getall() {
        List<CategoryDto> li = s.getall();
        return new ResponseEntity<List<CategoryDto>>(li, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getbyID(@PathVariable int id) {
        CategoryDto cd = s.getbyid(id);
        return new ResponseEntity<CategoryDto>(cd, HttpStatus.FOUND);
    }

}
