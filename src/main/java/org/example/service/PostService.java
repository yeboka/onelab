package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Post;
import org.example.model.User;
import org.example.model.dto.PostDTORecord;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {


    private final IPostRepository postRepository;
    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostDTORecord> getPostsFeed(Long id) {
        List<Post> posts = new ArrayList<>();
        User user = userRepository.findById(id).isPresent() ?
                userRepository.findById(id).get() : new User();

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


    private List<PostDTORecord> postDTOMapper(List<Post> posts) {
        return posts.stream().map(
                        post -> new PostDTORecord(post.getId(),
                                post.getDescription(),
                                post.getNumOfLikes())
                )
                .collect(Collectors.toList());
    }

}
