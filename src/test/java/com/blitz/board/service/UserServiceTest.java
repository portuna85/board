package com.blitz.board.service;

import com.blitz.board.domain.User;
import com.blitz.board.repository.UserRepositoryImpl;
import com.blitz.board.service.dto.UserDto;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void 회원가입() {
        // given
        String username = "JSBAE";
        String password = "sdlkjfs;jdfs";
        String email = "por@mail.com";
        String nickname = "Ronald";

        UserDto.Request save = UserDto.Request
                .builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .build();
        userService.saveUser(save);

        // when


        // then
    }
}