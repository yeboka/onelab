package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.dto.CommentDTO;
import org.example.repository.ICommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class CommentRepository implements ICommentRepository {

    private List<CommentDTO> comments;
    @Override
    public void save(CommentDTO comment) {
        comments.add(comment);
    }

    @Override
    public CommentDTO findById(Long id) {
        return comments.stream().filter(comment -> comment.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<CommentDTO> findAll() {
        return comments;
    }

    @Override
    public void removeById(Long id) {
        comments.removeIf(comment -> comment.getId().equals(id));
    }
}
