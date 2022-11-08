package com.blitz.board.service.dto;


import com.blitz.board.domain.Role;
import com.blitz.board.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


public class UserDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;

        private String username;

        private String password;

        private String nickname;

        private String email;

        private Role role;

        private LocalDateTime createdDate;

        private LocalDateTime modifiedDate;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(role)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .build();
        }
    }

    @Getter
    public static class Response {

        private final Long id;
        private final String username;
        private final String nickname;
        private final String email;
        private final Role role;
        private final LocalDateTime modifiedDate;

        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.modifiedDate = user.getModifiedDate();
        }
    }
}
