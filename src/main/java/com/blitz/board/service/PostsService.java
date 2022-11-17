package com.blitz.board.service;

import com.blitz.board.domain.Posts;
import com.blitz.board.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public void savePost(Posts posts) {




        postsRepository.save(posts);
    }

    @Transactional(readOnly = true)
    public List<Posts> postsList() {

        return null;
    }

}
