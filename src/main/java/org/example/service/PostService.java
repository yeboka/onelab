package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.PostNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.model.Comment;
import org.example.model.CommentObserver;
import org.example.model.Post;
import org.example.model.User;
import org.example.model.dto.CommentDTORecord;
import org.example.model.dto.PostDTORecord;
import org.example.repository.ICommentRepository;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {


    private final IPostRepository postRepository;
    private final IUserRepository userRepository;
    private final ICommentRepository commentRepository;
    private final CommentObserver commentObserver;

    @Transactional(readOnly = true)
    public List<PostDTORecord> getPostsFeed(Long id) throws UserNotFoundException {
        List<Post> posts = new ArrayList<>();
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %d doesn't exist!", id)));


        user.getSubscriptions().
                forEach(subscription -> posts.addAll(postRepository.findAllByAuthor(subscription)));

        return postDTOMapper(posts);
    }

    @Transactional
    public void removeFirstPostWithMinAmountOfLikes() {
        Post post = postRepository.findPostsWithMinAmountOfLikes().get(0);

        postRepository.delete(post);
    }


    @Transactional(readOnly = true)
    public List<PostDTORecord> searchPostsStartsWith(String text) {
        return postDTOMapper(postRepository.findByDescriptionStartingWith(text));
    }

    public PostDTORecord getPostOfUser(Long userId, Integer postId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %d doesn't exist!", userId)));
        Post post = postRepository.findByIdAndAuthor(postId, user);

        return new PostDTORecord(post.getId(), post.getDescription(), post.getNumOfLikes());
    }


    public void addComment(Integer postId, String commentStr) throws PostNotFoundException {
        Post post = postRepository.findById(Long.valueOf(postId)).orElseThrow(
                () -> new PostNotFoundException("Post doesn't found"));

        Comment comment = Comment.builder()
                .post(post)
                .author(post.getAuthor())
                .text(commentStr)
                .build();

        commentRepository.save(comment);
        commentObserver.update(post, commentStr);
    }

    public List<CommentDTORecord> getCommentsOfPost(Integer postId) throws PostNotFoundException {
        Post post = postRepository.findById(Long.valueOf(postId)).orElseThrow(
                () -> new PostNotFoundException("Post doesn't found"));

        List<Comment> comments = commentRepository.findCommentsByPost(post);

        return commentDTOMapper(comments);
    }

    private List<PostDTORecord> postDTOMapper(List<Post> posts) {
        return posts.stream().map(
                        post -> new PostDTORecord(post.getId(),
                                post.getDescription(),
                                post.getNumOfLikes())
                )
                .toList();
    }

    private List<CommentDTORecord> commentDTOMapper(List<Comment> comments) {
        return comments.stream().map(
                        comment -> new CommentDTORecord(comment.getId(),
                                comment.getPost().getId(),
                                comment.getAuthor().getId(),
                                comment.getText())
                )
                .toList();
    }

}
