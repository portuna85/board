package com.blitz.board.repository;

import com.blitz.board.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Slf4j
@Repository
public class UserRepositoryImplV2 implements UserRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public UserRepositoryImplV2(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("id")
                .usingColumns("username", "password", "nickname", "email", "role",
                        "created_date", "modified_date");
    }

    @Override
    public User join(User user) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);

        Number key = jdbcInsert.executeAndReturnKey(param);

        user.setId(key.longValue());
        return user;
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT id, " +
                " email, " +
                " nickname, " +
                " password, " +
                " role, " +
                " username, " +
                " created_date, " +
                " modified_date " +
                " FROM user " +
                " WHERE id = :id";
        try {
            Map<String, Object> param = Map.of("id", id);
            User user = template.queryForObject(sql, param, userRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<User> findByLoginId(String username) {
        String sql = "SELECT id, nickname, password FROM user WHERE username = :username";

        try {
            Map<String, Object> param = Map.of("username", username);
            User user = template.queryForObject(sql, param, userRowMapper());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long userId) {
        String sql = "DELETE FROM user WHERE id = ?";
        // template.update(sql, userId);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return template.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return BeanPropertyRowMapper.newInstance(User.class); //camel 변환 지원
    }
}
