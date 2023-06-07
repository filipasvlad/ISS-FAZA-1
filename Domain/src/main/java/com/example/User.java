package com.example;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
@Table( name = "user" )
public class User extends com.example.Entity<String>{
    private String username;
    private String pass;
    private String type;

    public User(String username, String pass, String type){
        this.username = username;
        this.pass = pass;
        this.type = type;
    }

    public User() {
        // this form used by Hibernate
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPass(), user.getPass()) && Objects.equals(getType(), user.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPass(), getType());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    public String getUsername() {
        return username;
    }
}
