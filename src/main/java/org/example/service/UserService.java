package org.example.service;

import org.example.model.Post;
import org.example.model.User;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

    @Autowired
    public UserService(IUserRepository userRepository, IPostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getMostPopularUser() {
        List<User> users = userRepository.findAll();
        return users.stream().max(Comparator.comparingInt(user -> user.getSubscribers().size())).orElse(null);
    }

    @Transactional
    public List<Post> getPopularPostsOfUser(User user) {
        return postRepository.findPostWithMostLikes(user);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostOfSubscribersOfUser(Long id) {
        List<Post> posts = new ArrayList<>();
        User user = userRepository.findById(id).get();

        for (User subscription :
                user.getSubscriptions()) {
            posts.addAll(postRepository.findAllByAuthor(subscription.getId()));
        }

        return posts;
    }

    @Transactional
    public void removeFirstPostWithMinAmountOfLikes() {
        Post post = postRepository.findPostsWithMinAmountOfLikes().get(0);

        postRepository.delete(post);
    }

    @Transactional
    public void subscribe(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User subscriber = userRepository.findById(subscriberId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.getSubscribers().add(subscriber);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Post> searchPostsStartsWith(String text) {
        return postRepository.findByDescriptionStartingWith(text);
    }


}
