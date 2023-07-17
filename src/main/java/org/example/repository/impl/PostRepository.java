package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(PostDTO post) {
        jdbcTemplate.update("INSERT INTO Post VALUES(?, ?, ?)", post.getId(), post.getAuthor_id(), post.getDescription());
    }


    @Override
    public PostDTO findById(Long id) {
        String sql = "SELECT * FROM Post WHERE id=? ";
        RowMapper<PostDTO> rowMapper = getPostRowMapper();

        return jdbcTemplate.query(sql, rowMapper, id).stream().findAny().orElse(null);
    }

    @Override
    public List<PostDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM Post", new BeanPropertyRowMapper<>(PostDTO.class));
    }

    @Override
    public void removeById(Long id) {
        jdbcTemplate.update("DELETE FROM Post WHERE id=?", id);
    }

    @Override
    public List<PostDTO> getAllPostsOfUser(UserDTO userDTO) {
        String sql = "SELECT * FROM Post WHERE author_id=? ";
        RowMapper<PostDTO> rowMapper = getPostRowMapper();

        return jdbcTemplate.query(sql, rowMapper, userDTO.getId());
    }

    private RowMapper<PostDTO> getPostRowMapper () {
        return (rs, rowNum) -> {
            PostDTO post = new PostDTO();
            post.setId(rs.getLong("id"));
            post.setAuthor_id(rs.getLong("author_id"));
            post.setDescription(rs.getString("description"));
            return post;
        };
    }


}
