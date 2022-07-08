package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name="user_id")
    @Column(name="yourQuizzes", nullable = false)
    private List<Quiz> yourQuizzes = new ArrayList<Quiz>();

    public List<Quiz> getYourQuizzes() {
        return yourQuizzes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String firstName) {
        this.nickname = firstName;
    }
}