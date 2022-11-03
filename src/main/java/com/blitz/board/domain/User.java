package com.blitz.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    private List<Posts> posts;
    private List<Comment> comments;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public User() {
    }

    @Builder
    public User(Long id, String username, String password, String nickname, String email, Role role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
