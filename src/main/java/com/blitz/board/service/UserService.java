package com.blitz.board.service;

import com.blitz.board.domain.User;
import com.blitz.board.repository.UserRepository;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserDto.Request dto) {
        userRepository.join(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
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
