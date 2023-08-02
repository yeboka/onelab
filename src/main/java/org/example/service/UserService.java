package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.CommentObserver;
import org.example.model.Post;
import org.example.model.User;
import org.example.model.dto.CommentNotificationRecord;
import org.example.model.dto.PostDTORecord;
import org.example.model.dto.ProfileDTORecord;
import org.example.model.dto.UserDTORecord;
import org.example.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService implements CommentObserver {
    private final IUserRepository userRepository;
    private final JmsService jmsService;

    @Transactional(readOnly = true)
    public List<UserDTORecord> list() {
        List<User> users = userRepository.findAll();
        return userDTOMapper(users);
    }

    @Transactional(readOnly = true)
    public UserDTORecord getMostPopularUser() {
        User user = userRepository.findAll().stream().max(
                Comparator.comparingInt(temp -> temp.getSubscribers().size())
        ).orElse(new User());

        return new UserDTORecord(user.getId(),
                user.getName(),
                user.getAge());
    }

    @Transactional
    public void subscribe(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User subscriber = userRepository.findById(subscriberId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.getSubscribers().add(subscriber);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ProfileDTORecord getProfileOfMostPopularUser() throws ExecutionException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture<ProfileDTORecord> userInfo = CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject("http://localhost:8000/api/v1/public/most_popular_user", UserDTORecord.class)
        ).thenApply(
                response -> {
                    PostDTORecord[] posts = restTemplate.getForObject(String.format("http://localhost:8000/api/v1/public/%d/profile", response.id()), PostDTORecord[].class);
                    assert posts != null;
                    return new ProfileDTORecord(response.name(), Arrays.stream(posts).toList());
                }
        );

        return userInfo.get();
    }

    @Override
    public void update(Post post, String commentText) {
        String topic = "test";

        CommentNotificationRecord notificationRecord =
                new CommentNotificationRecord(post.getAuthor().getId(),
                        post.getId(),
                        commentText);
        jmsService.sendCommentNotification(topic, notificationRecord);
    }

    private List<UserDTORecord> userDTOMapper(List<User> users) {
        return users.stream().map(
                        user -> new UserDTORecord(user.getId(),
                                user.getName(),
                                user.getAge()))
                .toList();
    }
}


