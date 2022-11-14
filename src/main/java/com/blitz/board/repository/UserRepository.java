package com.blitz.board.repository;

import com.blitz.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User join(User user);

    Optional<User> findById(Long userID);

    List<User> findAll();

    Optional<User> findByLoginId(String userId);

    void delete(Long userId);

}
