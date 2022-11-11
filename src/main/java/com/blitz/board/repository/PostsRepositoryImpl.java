package com.blitz.board.repository;

import com.blitz.board.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Posts save(Posts posts) {
        String sql = "INSERT INTO Posts(id, title, content, writer, user_id , created_date, modified_date) " +
                " VALUES (null, ?, ?, ?, (SELECT nickname FROM user WHERE id = ?), (SELECT id FROM user WHERE id = ?), now(), now())";


        return null;
    }

    @Override
    public Posts findById(Long postsId) {
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
