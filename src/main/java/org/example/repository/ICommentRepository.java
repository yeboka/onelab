package org.example.repository;

import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;

import java.util.List;

public interface ICommentRepository {
    void save(CommentDTO comment);

    CommentDTO findById(Long id);

    List<CommentDTO> findAll();

    void removeById (Long id);

    List<CommentDTO> getAllCommentsOfPost(PostDTO post);

    List<CommentDTO> getAllCommentsOfPostOfUser(PostDTO post, UserDTO user);

    void createTable();

    void dropTable();
}
