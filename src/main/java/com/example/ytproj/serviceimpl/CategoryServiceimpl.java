
package com.example.ytproj.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.Catrgory;
import com.example.ytproj.entities.User;
import com.example.ytproj.payload.CategoryDto;
import com.example.ytproj.repositries.Categoryrepo;
import com.example.ytproj.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    Categoryrepo rs;
    @Autowired
    ModelMapper mm;

    @Override
    public CategoryDto createuser(CategoryDto ud) {
        // TODO Auto-generated method stub
        Catrgory ct = mm.map(ud, Catrgory.class);
        Catrgory td = rs.save(ct);
        return mm.map(td, CategoryDto.class);
    }

    @Override
    public CategoryDto updateteuser(CategoryDto ud, int id) {
        // TODO Auto-generated method stub
        Catrgory category = rs.findById(id).get();
        category.setDescription(ud.getDescription());
        category.setId(ud.getId());
        category.setTitle(ud.getTitle());
        Catrgory ncs = rs.save(category);
        return mm.map(ncs, CategoryDto.class);
    }

    @Override
    public CategoryDto getbyid(int id) {
        // TODO Auto-generated method stub
        Catrgory catrgory = rs.findById(id).get();
        CategoryDto ud = mm.map(catrgory, CategoryDto.class);
        return ud;
    }

    @Override
    public void deletebyid(int id) {
        // TODO Auto-generated method stub
        rs.deleteById(id);
    }

    @Override
    public List<CategoryDto> getall() {
        // TODO Auto-generated method stub

        List<Catrgory> li = (List<Catrgory>) rs.findAll();
        List<CategoryDto> nli = new ArrayList<>();
        li.forEach(i -> nli.add(mm.map(i, CategoryDto.class)));
        return nli;

    }

}
