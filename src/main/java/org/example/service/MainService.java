package org.example.service;

import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.impl.CommentRepository;
import org.example.repository.impl.PostRepository;
import org.example.repository.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class


MainService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MainService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initTables() {
//        String sql = "CREATE TABLE `user` (id BIGINT PRIMARY KEY, name VARCHAR(255), age INT)";

//        jdbcTemplate.execute(sql);
    }

    public void save(UserDTO userDTO) {
        userRepository.save(userDTO);
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> findAllUser() {
        return userRepository.findAll();
    }

    public void removeUserById(Long id) {
        userRepository.removeById(id);
    }

    public void createPost(UserDTO user, PostDTO post) {
        postRepository.save(post);
    }

    public List<PostDTO> getAllPostsOfUser(UserDTO userDTO) {
        return postRepository.getAllPostsOfUser(userDTO);
    }

    public void addComment(UserDTO user, PostDTO post, CommentDTO comment) {
        commentRepository.save(comment);
    }

    public List<CommentDTO> getAllCommentsOfPost (PostDTO post) {
        return commentRepository.getAllCommentsOfPost(post);
    }

    public List<CommentDTO> getAllCommentsOfPostOfUser (PostDTO post, UserDTO user) {
        return commentRepository.getAllCommentsOfPostOfUser(post, user);
    }
}
