package com.blitz.board.repository;

import com.blitz.board.domain.Posts;

import java.util.List;

public interface PostsRepository {

    Posts save(Posts posts);

    List<Posts> findAll();

    void delete(Long postsId);
}