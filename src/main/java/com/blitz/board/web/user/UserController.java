package com.blitz.board.web.user;

import com.blitz.board.domain.User;
import com.blitz.board.service.UserService;
import com.blitz.board.service.dto.LoginDto;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String joinForm(@ModelAttribute("userDto") UserDto.Request dto, Model model) {
        model.addAttribute("UserDto", dto);
        return "users/createUser";
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String save(@ModelAttribute("userDto") UserDto.Request dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/createUser";
        }

        userService.saveUser(dto);
        return "redirect:/";
    }

    /**
     * 회원상세 정보
     */
    @GetMapping("/user/{userId}")
    public String findUser(@PathVariable("userId") User userId, Model model) {
        log.info("userId = {}", userId);
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") LoginDto dto) {
        return "/users/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto dto, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "users/loginForm";
        }

        User loginMember = userService.login(dto.getLoginId(), dto.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "users/loginForm";
        }

        // 로그인 성공시
        Cookie idCookie = new Cookie("userId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }
}
