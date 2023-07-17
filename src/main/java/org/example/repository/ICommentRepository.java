package org.example.repository;

import org.example.dto.CommentDTO;

import java.util.List;

public interface ICommentRepository {
    void save(CommentDTO comment);

    CommentDTO findById(Long id);

    List<CommentDTO> findAll();

    void removeById (Long id);
}
