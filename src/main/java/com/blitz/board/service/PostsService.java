package com.blitz.board.service;

import com.blitz.board.domain.Posts;
import com.blitz.board.repository.PostsRepository;
import com.blitz.board.service.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public void savePost(PostsDto.Request dto) {

        dto.getContent();
        dto.getTitle();
        dto.getUser().getNickname();
        dto.getUser().getId();


        postsRepository.save(dto);
    }

    @Transactional(readOnly = true)
    public List<Posts> postsList() {

        return null;
    }

}
