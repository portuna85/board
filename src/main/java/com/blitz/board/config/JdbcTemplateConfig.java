package com.blitz.board.config;

import com.blitz.board.repository.PostsRepository;
import com.blitz.board.repository.PostsRepositoryImpl;
import com.blitz.board.repository.UserRepository;
import com.blitz.board.repository.UserRepositoryImpl;
import com.blitz.board.service.PostsService;
import com.blitz.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public PostsService postsService() {
        return new PostsService(postsRepository());
    }

    @Bean
    public PostsRepository postsRepository() {
        return new PostsRepositoryImpl(dataSource);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(dataSource);
    }
}
