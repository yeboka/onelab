package org.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDTO {

    private Long id;
    private Long postId;
    private Long authorId;
    private String text;
}
