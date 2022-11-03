package com.blitz.board.repository;

import com.blitz.board.domain.User;
import com.blitz.board.service.dto.UserDto;

import java.util.Optional;


public interface UserRepository {
    User join(User user);

    void modifyUser(Long userId, UserDto.Request dto);

    void modifyPassword(Long userId, UserDto.Request dto);

    void modifyEmail(Long userId, UserDto.Request dto);

    void modifyNickname(Long userId, UserDto.Request dto);

    Optional<User> findByUser(Long id);

    void delete(Long userID);
}
