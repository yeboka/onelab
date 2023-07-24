package org.example.repository;

import org.example.model.Post;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long>, QueryByExampleExecutor<Post> {

    @Query("SELECT p FROM Post p WHERE p.numOfLikes = (SELECT MAX(p2.numOfLikes) FROM Post p2 WHERE p2.author = :userId)")
    List<Post> findPostWithMostLikes(@Param("userId") User id);

    @Query("SELECT p FROM Post p WHERE p.numOfLikes = (SELECT MIN(p2.numOfLikes) FROM Post p2)")
    List<Post> findPostsWithMinAmountOfLikes();

    List<Post> findByDescriptionStartingWith(String description);

    List<Post> findAllByAuthor(Long id);
}
