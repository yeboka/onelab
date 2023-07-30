package org.example.repository;

import org.example.model.Post;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long>, QueryByExampleExecutor<Post> {

    @Query("SELECT p FROM Post p WHERE p.numOfLikes = (SELECT MIN(p2.numOfLikes) FROM Post p2)")
    List<Post> findPostsWithMinAmountOfLikes();

    List<Post> findByDescriptionStartingWith(String description);

    Post findByIdAndAuthor(Integer id, User user);

    List<Post> findAllByAuthor(User author);
}
