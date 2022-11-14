package com.blitz.board.repository;

import com.blitz.board.domain.User;
import com.blitz.board.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("id")
                .usingColumns("title", "content", "writer", "view", "createdDate", "modifiedDate");
    }

    @Override
    public User join(User user) {
        /**
         * TODO
         *  sql문을 StringBuffer 또는 StringBuilder 사용
         */
        String sql = "INSERT INTO user( username, password, nickname, email,  created_date, modified_date)" +
                " VALUES (:username, :password, :nickname, :email, now(), now())";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);
        user.setRole(Role.BRONZE);
        long key = keyHolder.getKey().longValue();
        user.setId(key);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
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
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByLoginId(String username) {
        String sql = "SELECT id, username, nickname, password FROM user WHERE username = :username";

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
        //template.update(sql, userId);
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
