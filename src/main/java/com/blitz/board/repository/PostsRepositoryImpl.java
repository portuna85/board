package com.blitz.board.repository;

import com.blitz.board.domain.Posts;
import com.blitz.board.service.dto.PostsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
@Repository
public class PostsRepositoryImpl implements PostsRepository {

    private final JdbcTemplate template;

    public PostsRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Posts save(Posts posts) {
        String sql = "INSERT INTO posts(id, content, title, writer, user_id)" +
                " VALUES (null, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            log.info("dtoValues = {}", posts.getTitle());
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, posts.getContent());
            ps.setString(2, posts.getTitle());
            ps.setString(3, posts.getUser().getNickname());
            ps.setLong(4, posts.getUser().getId());
            return ps;
        }, keyHolder);
        posts.setId(keyHolder.getKey().longValue());

        return new Posts();
    }

    @Override
    public List<Posts> findAll() {
        return null;
    }

    @Override
    public void delete(Long postsId) {

    }
}
