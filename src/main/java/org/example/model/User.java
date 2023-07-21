package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "author")
    List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "subscriptions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    List<User> subscribers = new ArrayList<>();

    @ManyToMany(mappedBy = "subscribers")
    List<User> subscriptions = new ArrayList<>();
}
