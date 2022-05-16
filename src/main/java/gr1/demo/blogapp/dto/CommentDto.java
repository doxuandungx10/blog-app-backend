package gr1.demo.blogapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {
    private String Comment;
    private Long postId;
    private String username;
}
