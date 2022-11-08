package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.service.UserService;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String joinForm(UserDto.Request dto, Model model) {
        log.info("DTO USERNAME = {}", dto.getUsername());
        model.addAttribute("UserDto", dto);
        return "users/createUser";
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String joinForm(@ModelAttribute UserDto.Request UserDto) {
        log.info("POST USERNAME = {}", UserDto.getUsername());
        userService.saveUser(UserDto);

        return "redirect:/";
    }

    /**
     * 회원상세 정보
     */
    @GetMapping("/user/{userId}")
    public String findUser(@PathVariable("userId") Long userId, Model model) {
        log.info("userId = {}", userId);
        User user = userService.findUser(userId).get();
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/user/{userId}/edit")
    public String modifyPassword(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findUser(userId).get();
        model.addAttribute("user", user);
        return "users/editPassword";
    }

    @PostMapping("/{itemId}/edit")
    public String modifyPassword(@PathVariable("userId") Long userId, @ModelAttribute UserDto.Request dto) {
        userService.modifyPassword(userId, dto);
        return "redirect:/users/{userId}";
    }
}
