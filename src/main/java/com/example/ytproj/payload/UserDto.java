package com.example.ytproj.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.ytproj.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class UserDto {

    int id;
    @NotEmpty
    String name;
    @Email
    String email;
    @NotEmpty
    String password;
    @NotEmpty
    String about;

    String imagename;
    boolean isPrivate;

    public boolean getisPrivate() {
        return this.isPrivate;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String pass) {
        this.password = pass;
    }

    Set<RoleDto> set = new HashSet<>();
}