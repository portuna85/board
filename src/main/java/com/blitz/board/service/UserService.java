package com.blitz.board.service;

import com.blitz.board.domain.User;
import com.blitz.board.repository.UserRepository;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserDto.Request dto) {
        userRepository.join(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(Long userId) {
        return userRepository.findByUser(userId);
    }

}
