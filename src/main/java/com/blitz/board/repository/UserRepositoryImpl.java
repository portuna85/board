package com.blitz.board.repository;

import com.blitz.board.domain.Role;
import com.blitz.board.domain.User;
import com.blitz.board.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate template;

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
    public void modifyUser(Long userId, UserDto.Request dto) {
        String sql = "UPDATE user " +
                " SET nickname = ?, password = ?, email = ?, modified_date = now() " +
                " WHERE user_id = ?";

        log.info("Repository dto = {}", dto);

        template.update(sql, dto.getNickname(), dto.getPassword(), dto.getEmail(), userId);
    }

    @Override
    public void modifyPassword(Long userId, UserDto.Request dto) {
        String sql = "UPDATE user SET password = ? WHERE user_id = ?";
        log.info("Repo PassWord Modify = {}", dto);
        template.update(sql, dto.getPassword(), userId);
    }

    @Override
    public void modifyNickname(Long userId, UserDto.Request dto) {
        String sql = "UPDATE user SET nickname = ? WHERE user_id = ?";
        log.info("Repo NickName Modify = {}", dto);
        template.update(sql, dto.getNickname(), userId);
    }

    @Override
    public void modifyEmail(Long userId, UserDto.Request dto) {
        String sql = "UPDATE user SET email = ? WHERE user_id = ?";
        log.info("Repo Email Modify = {}", dto);
        template.update(sql, dto.getEmail(), userId);
    }

    @Override
    public Optional<User> findByUser(Long id) {
        String sql = "SELECT user_id, " +
                " email, " +
                " nickname, " +
                " password, " +
                " role, " +
                " username, " +
                " created_date, " +
                " modified_date " +
                " FROM user " +
                " WHERE user_id = ?";
        try {
            User user = template.queryForObject(sql, userRowMapper(), id);
            return Optional.of(user);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long userID) {
        String sql = "DELETE FROM user WHERE id= ?";
        template.update(sql);
    }

    private RowMapper<User> userRowMapper() {
        return BeanPropertyRowMapper.newInstance(User.class); //camel 변환 지원
    }
}
