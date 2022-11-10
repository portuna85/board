package com.blitz.board.service.dto;

import com.blitz.board.domain.Role;
import com.blitz.board.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserDto {

    @Data
    public static class Request {
        private Long id;

        private String username;

        private String password;

        private String nickname;

        private String email;

        private Role role;

        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Request() {
        }

        @Builder
        public Request(Long id, String username, String password, String nickname, String email, Role role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.nickname = nickname;
            this.email = email;
            this.role = role;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
        }

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
