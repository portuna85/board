package com.blitz.board.web;

import com.blitz.board.repository.UserRepository;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // @GetMapping("/")
    public String home(@CookieValue(name = "userId", required = false) UserDto.Request userId, Model model) {
        if (userId == null) {
            return "index";
        }

        // userRepository.findById(userId);

        return "index";
    }
}
