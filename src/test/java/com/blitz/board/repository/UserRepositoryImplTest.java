package com.blitz.board.repository;

import com.blitz.board.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserRepositoryImplTest {

    UserRepositoryImpl userRepository;

    @Test
    public void 회원가입() {
        // given
        User user = User.builder()
                .username("portuna85")
                .password("slkjdf")
                .nickname("융개소문")
                .email("portuna85@gmail.com")
                .build();

        // when
        User savedUser = userRepository.join(user);

        // then
        assertThat(user.getId()).isEqualTo(savedUser.getId());
    }

}
