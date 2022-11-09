package com.blitz.board.web;

import com.blitz.board.domain.User;
import com.blitz.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    // @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String index(@CookieValue(name = "userId", required = false) Long userId, Model model) {
        if (userId == null) {
            return "index";
        }

        User loginUser = userRepository.findById(userId);

        if (loginUser == null) {
            return "index";
        }

        model.addAttribute("user", loginUser);
        return "loginHome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "userId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
