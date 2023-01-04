package com.example.ytproj.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resps")
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Likes [lid=" + lid + ", uid=" + uid + ", pid=" + pid + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int lid;
    int uid;
    int pid;

}
