package com.blitz.board.config;

import com.blitz.board.repository.UserRepository;
import com.blitz.board.repository.UserRepositoryImplV1;
import com.blitz.board.repository.UserRepositoryImplV2;
import com.blitz.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV1Config {

    private final DataSource dataSource;

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImplV1(dataSource);
    }
}
