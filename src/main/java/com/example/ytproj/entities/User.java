package com.example.ytproj.entities;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Override
    public String toString() {
        return "User [id=" + id + ", isPrivate=" + isPrivate + ", name=" + name + ", email=" + email + ", password="
                + password + ", about=" + about + ", imagename=" + imagename + ", li=" + li + ", cmnt=" + cmnt
                + ", set=" + set + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    boolean isPrivate;
    String name;
    String email;
    String password;
    String about;
    @Column(name = "imagename")
    String imagename;

    @OneToMany(mappedBy = "u", cascade = CascadeType.ALL)
    List<Post> li = new ArrayList<>();
    @OneToMany
    List<Comment> cmnt = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    Set<Role> set = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> li = set.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return li;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generasted method stub
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}