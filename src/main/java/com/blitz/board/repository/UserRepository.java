package com.blitz.board.repository;

import com.blitz.board.domain.User;
import com.blitz.board.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User join(User user);

    Optional<User> findByUser(Long userId);

    void modifyPassword(Long userId, UserDto.Request password);

    void modifyEmail(Long userId, UserDto.Request email);

    void modifyNickname(Long userId, UserDto.Request nickname);

    List<User> findAll();

    void delete(Long userID);

}
