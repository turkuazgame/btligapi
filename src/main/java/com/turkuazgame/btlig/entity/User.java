package com.turkuazgame.btlig.entity;

import java.util.Collection;

import com.turkuazgame.btlig.request.CompetitorRequest;
import com.turkuazgame.btlig.request.UserRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Data;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="user_name")
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="user_password")
    private String password;

    @Column(name="user_image")
    private String userImage;

    public User(UserRequest request) {
        this.setUserId(request.getUserId());
        this.setUsername(request.getUsername());
        this.setFirstName(request.getFirstName());
        this.setLastName(request.getLastName());
        this.setEmail(request.getEmail());
        this.setPassword(request.getPassword());
        this.setUserImage(request.getUserImage());
    }

    public void setFromOther(User other) {
        this.setFirstName(other.getFirstName());
        this.setLastName(other.getLastName());
        this.setEmail(other.getEmail());
        this.setUserImage(other.getUserImage());
    }
}