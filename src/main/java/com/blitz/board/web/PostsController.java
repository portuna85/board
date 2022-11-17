package com.blitz.board.web;

import com.blitz.board.domain.Posts;
import com.blitz.board.domain.User;
import com.blitz.board.service.PostsService;
import com.blitz.board.service.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/write")
    public String postForm(@ModelAttribute("postsDto") PostsDto.Request dto, Model model) {

        model.addAttribute("postsDto", dto);

        return "posts/addPosts";
    }

    @PostMapping("/write")
    public String addPosts(@ModelAttribute("postsDto") Posts posts) {
        postsService.savePost(posts);

        return "redirect:/";
    }
}
