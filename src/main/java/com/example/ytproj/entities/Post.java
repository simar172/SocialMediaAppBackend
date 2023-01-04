package com.example.ytproj.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String title;
    @Column(length = 100000)
    String content;
    String imagename;
    Date date;
    @ManyToOne
    Catrgory ct;
    @ManyToOne
    User u;
    @OneToMany(mappedBy = "p", cascade = CascadeType.ALL)
    List<Comment> li = new ArrayList<>();
   
}