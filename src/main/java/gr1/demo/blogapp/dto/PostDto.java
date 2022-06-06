package gr1.demo.blogapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String content;
    private String title;
    private String username;
    private String catelogy;

}
