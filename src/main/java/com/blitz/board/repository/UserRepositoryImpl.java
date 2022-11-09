package com.blitz.board.repository;

import com.blitz.board.domain.User;
import com.blitz.board.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User join(User user) {
        /**
         * TODO
         *  sql문을 StringBuffer 또는 StringBuilder 사용
         */
        String sql = "INSERT INTO user(username, password, nickname, email, role, created_date, modified_date)" +
                " VALUES (?, ?, ?, ?, ?, now(), now())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            log.info("dtoValues = {}", user.getUsername());
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            ps.setString(5, Role.BRONZE.getValue());
            return ps;
        }, keyHolder);
        long lastInsertId = keyHolder.getKey().longValue();
        user.setId(lastInsertId);
        return user;
    }

    @Override
    public User findById(User userId) {
        String sql = "SELECT id, " +
                " email, " +
                " nickname, " +
                " password, " +
                " role, " +
                " username, " +
                " created_date, " +
                " modified_date " +
                " FROM user " +
                " WHERE id = ?";
        try {
            User aLong = template.queryForObject(sql, userRowMapper(), userId);
            return aLong;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<User> findByLoginId(String userId) {
        List<User> result = template.query("SELECT id, username, password, nickname, email FROM user WHERE username = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public void delete(Long userId) {
        String sql = "DELETE FROM user WHERE id= ?";
        template.update(sql, userId);
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
