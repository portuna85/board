package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "index";
        }

        User loginUser = (User) session.getAttribute(SessionConst.LOGIN_USER);

        // 세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "index";
        }

        // Session 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);

        return "loginHome";
    }
}
