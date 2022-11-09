package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManager sessionManager;

    // @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {

        User user = (User) sessionManager.getSession(request);

        if (user == null) {
            return "index";
        }

        model.addAttribute("user", user);
        return "loginHome";
    }
}
