package com.example.ytproj;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.ytproj.entities.Catrgory;
import com.example.ytproj.entities.Role;
import com.example.ytproj.entities.User;
import com.example.ytproj.repositries.Categoryrepo;
import com.example.ytproj.repositries.PostRepo;
import com.example.ytproj.repositries.Repo;
import com.example.ytproj.repositries.Role_repo;

@SpringBootApplication
public class YtprojApplication implements CommandLineRunner {

    @Autowired
    PasswordEncoder pass;

    @Autowired
    Repo r;
    @Autowired
    Role_repo roleRepo;
    @Autowired
    Categoryrepo cr;

    public static void main(String[] args) {
        SpringApplication.run(YtprojApplication.class, args);

    }

    @Bean
    public ModelMapper modelmap() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        Role role = new Role();
        role.setId(501);
        role.setName("ROLE_ADMIN");

        Role role1 = new Role();
        role1.setId(502);
        role1.setName("ROLE_NORMAL");

        List<Role> roles = List.of(role, role1);

        List<Role> result = this.roleRepo.saveAll(roles);
        Catrgory ct = new Catrgory();
        ct.setId(1);
        ct.setDescription("Java");
        ct.setTitle("java");
        cr.save(ct);

    }

}
