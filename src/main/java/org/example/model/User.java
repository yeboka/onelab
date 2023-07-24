package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
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
