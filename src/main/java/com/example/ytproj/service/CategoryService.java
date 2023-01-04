package com.example.ytproj.service;

import com.example.ytproj.payload.CategoryDto;
import java.util.*;

public interface CategoryService {
    CategoryDto createuser(CategoryDto ud);

    CategoryDto updateteuser(CategoryDto ud, int id);

    CategoryDto getbyid(int id);

    void deletebyid(int id);

    List<CategoryDto> getall();

}
