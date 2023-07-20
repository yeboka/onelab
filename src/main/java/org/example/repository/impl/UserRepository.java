package org.example.repository.impl;

import org.example.dto.UserDTO;
import org.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public void save(UserDTO user) {
        jdbcTemplate.update("INSERT INTO `user` (id, name, age) VALUES(?, ?, ?)", user.getId(), user.getName(), user.getAge());
    }

    @Override
    public UserDTO findById(Long id) {
        String sql = "SELECT * FROM `user` WHERE id=? ";
        RowMapper<UserDTO> rowMapper = (rs, rowNum) -> {
            UserDTO user = new UserDTO();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            return user;
        };

        return jdbcTemplate.query(sql, rowMapper, id).stream().findAny().orElse(null);
    }

    @Override
    public List<UserDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM `user`", new BeanPropertyRowMapper<>(UserDTO.class));
    }

    @Override
    public void removeById(Long id) {
        jdbcTemplate.update("DELETE FROM `user` WHERE id=?", id);
    }


}



