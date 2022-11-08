package com.blitz.board.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    public String getRoleValue() {
        return this.role.getValue();
    }
}
