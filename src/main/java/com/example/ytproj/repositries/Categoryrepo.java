package com.example.ytproj.repositries;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ytproj.entities.Catrgory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ytproj.entities.Catrgory;

//import com.example.ytproj.payload.CategoryDto;
@Repository
public interface Categoryrepo extends CrudRepository<Catrgory, Integer> {

}
