package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "numOfLikes")
    private Integer numOfLikes;

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();

}