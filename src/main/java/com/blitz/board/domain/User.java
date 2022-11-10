package com.blitz.board.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

    @NotEmpty
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
