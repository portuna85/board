package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession httpSession) {

        String sessionName = null;

        HttpSession session = request.getSession(false);

        final Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                log.info("cookieValue = {}, cookieName = {}", cookies[i].getValue(), cookies[i].getName());

                if (cookies[i].getName().equals("JSESSIONID")) {
                    sessionName = cookies[i].getValue();
                }
            }
        }

        if (session != null) {
            sessionName.equals(session.getId());
            return "loginHome";
        } else {
            httpSession.setAttribute(sessionName, "computer2");
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
