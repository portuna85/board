package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequiredArgsConstructor
public class HomeController {
/*
    @GetMapping("/")
    public String index(@SessionAttribute(value = SessionConst.LOGIN_USER, required = false) User loginUser, Model model, HttpServletRequest request) {
        // 세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "index";
        }

        // Session 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);

        return "loginHome";
    }

*/

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null)
            return "index";

        User loginMember = (User) session.getAttribute(SessionConst.LOGIN_USER);

        if(loginMember == null)
            return "index";

        model.addAttribute("user", loginMember);
        return "loginHome";
    }
}
