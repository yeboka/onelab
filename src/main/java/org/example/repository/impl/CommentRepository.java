package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentRepository implements ICommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(CommentDTO comment) {
        jdbcTemplate.update("INSERT INTO `comment` (id, postId, authorId, text) VALUES(?, ?, ?, ?)", comment.getId(), comment.getPostId(), comment.getAuthorId(), comment.getText());
    }


    @Override
    public CommentDTO findById(Long id) {
        String sql = "SELECT * FROM `comment` WHERE id=? ";
        RowMapper<CommentDTO> rowMapper = getCommentRowMapper();

        return jdbcTemplate.query(sql, rowMapper, id).stream().findAny().orElse(null);
    }

    @Override
    public List<CommentDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM `comment`", new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    @Override
    public void removeById(Long id) {
        jdbcTemplate.update("DELETE FROM `comment` WHERE id=?", id);
    }

    @Override
    public List<CommentDTO> getAllCommentsOfPost(PostDTO post) {
        String sql = "SELECT * FROM `comment` WHERE postId=? ";
        RowMapper<CommentDTO> rowMapper = getCommentRowMapper();

        return jdbcTemplate.query(sql, rowMapper, post.getId());
    }

    @Override
    public List<CommentDTO> getAllCommentsOfPostOfUser(PostDTO post, UserDTO user) {
        String sql = "SELECT * FROM `comment` WHERE postId=? AND authorId=? ";
        RowMapper<CommentDTO> rowMapper = getCommentRowMapper();

        return jdbcTemplate.query(sql, rowMapper, post.getId(), user.getId());
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE `comment` (id BIGINT PRIMARY KEY, postId BIGINT, authorId BIGINT, text VARCHAR(255))";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE `comment`";
        jdbcTemplate.execute(sql);
    }

    private RowMapper<CommentDTO> getCommentRowMapper() {
        return (rs, rowNum) -> {
            CommentDTO comment = new CommentDTO();
            comment.setId(rs.getLong("id"));
            comment.setPostId(rs.getLong("postId"));
            comment.setAuthorId(rs.getLong("authorId"));
            comment.setText(rs.getString("text"));
            return comment;
        };
    }
}
