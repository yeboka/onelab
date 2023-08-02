package org.example.repository;

import org.example.model.Comment;
import org.example.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByPost(Post post);

    Comment findById(Integer id);
}
