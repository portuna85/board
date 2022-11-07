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
        log.info("UserDto = {}", dto);
        userRepository.join(dto.toEntity());
    }

    @Transactional
    public void modifyUser(Long userId, UserDto.Request user) {

        userRepository.modifyUser(userId, user);
    }

    @Transactional
    public void modifyPassword(Long userId, UserDto.Request user) {
        userRepository.modifyPassword(userId, user);
    }

    @Transactional
    public void modifyEmail(Long userId, UserDto.Request user) {
        userRepository.modifyEmail(userId, user);
    }

    @Transactional
    public void modifyNickname(Long userId, UserDto.Request user) {
        userRepository.modifyNickname(userId, user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(Long userId) {
        return userRepository.findByUser(userId);
    }
}
