package org.example.service;

import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.impl.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(UserDTO user, PostDTO post, CommentDTO comment) {
        commentRepository.save(comment);
    }

    public List<CommentDTO> getAllCommentsOfPost(PostDTO post) {
        return commentRepository.getAllCommentsOfPost(post);
    }

    public List<CommentDTO> getAllCommentsOfPostOfUser(PostDTO post, UserDTO user) {
        return commentRepository.getAllCommentsOfPostOfUser(post, user);
    }


}
