package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.service.UserService;
import com.blitz.board.service.dto.LoginDto;
import com.blitz.board.service.dto.UserDto;
import com.blitz.board.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String login(@Valid @ModelAttribute("loginDto") LoginDto dto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "users/loginForm";
        }

        User loginUser = userService.login(dto.getLoginId(), dto.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "users/loginForm";
        }

        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);
        
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        // sessionManager.expire(request);   - 세션 만료시 expire

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
