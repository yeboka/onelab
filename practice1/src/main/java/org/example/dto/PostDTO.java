package org.example.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostDTO {
    private Long id;
    private Long author_id;
    private String description;
    private List<CommentDTO> comments;
}
