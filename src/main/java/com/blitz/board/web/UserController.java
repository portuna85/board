package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.service.UserService;
import com.blitz.board.service.dto.LoginDto;
import com.blitz.board.service.dto.UserDto;
import com.blitz.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

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
    public String findUser(@PathVariable("userId") Long userId, Model model) {
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

        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
        sessionManager.createSession(loginMember, response);

        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        sessionManager.expire(request);
        return "redirect:/";
    }

    /*
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    */
}
