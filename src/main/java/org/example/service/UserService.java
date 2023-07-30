package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.model.dto.UserDTORecord;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

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

    private List<UserDTORecord> userDTOMapper(List<User> users) {
        return users.stream().map(
                        user -> new UserDTORecord(user.getId(),
                                user.getName(),
                                user.getAge()))
                .collect(Collectors.toList());
    }
}


