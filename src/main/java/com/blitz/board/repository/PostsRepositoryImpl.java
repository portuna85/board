package com.blitz.board.repository;

import com.blitz.board.domain.Posts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostsRepositoryImpl implements PostsRepository {

    private final JdbcTemplate template;

    public PostsRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Posts save(Posts posts) {
        String sql = "INSERT INTO posts(id, content, title, writer, user_id, created_date, modified_date)" +
                " VALUES (null, ?, ?, (SELECT nickname FROM user WHERE id = ?), (SELECT id FROM user WHERE id = ?), now(), now())";

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
