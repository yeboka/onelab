package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.model.dto.NewUserDTO;
import org.example.model.dto.UserDTO;
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

    @Transactional
    public UserDTO save(NewUserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .build();

        User response = userRepository.save(user);

        return UserDTO.builder()
                .id(response.getId())
                .name(response.getName())
                .age(response.getAge())
                .build();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> list() {
        List<User> users = userRepository.findAll();
        return userDTOMapper(users);
    }

    @Transactional(readOnly = true)
    public UserDTO getMostPopularUser() {
        User user = userRepository.findAll().stream().max(
                Comparator.comparingInt(temp -> temp.getSubscribers().size())
        ).orElse(new User());

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    @Transactional
    public void subscribe(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User subscriber = userRepository.findById(subscriberId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.getSubscribers().add(subscriber);
        userRepository.save(user);
    }

    private List<UserDTO> userDTOMapper(List<User> users) {
        return users.stream().map(
                        user -> UserDTO.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .age(user.getAge())
                                .build())
                .collect(Collectors.toList());
    }
}


