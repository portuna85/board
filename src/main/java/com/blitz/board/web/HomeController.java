package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.repository.UserRepository;
import com.blitz.board.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;


    @GetMapping("/")
    public String index(@SessionAttribute(value = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {

        // 세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "index";
        }

        // Session이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}
