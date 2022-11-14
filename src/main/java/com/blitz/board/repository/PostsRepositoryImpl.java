package com.blitz.board.repository;

import com.blitz.board.domain.Posts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostsRepositoryImpl implements PostsRepository {

    @Override
    public Posts save(Posts posts) {


        return null;
    }

    @Override
    public List<Posts> findAll() {
        return null;
    }

    @Override
    public void delete(Long postsId) {

    }
}
