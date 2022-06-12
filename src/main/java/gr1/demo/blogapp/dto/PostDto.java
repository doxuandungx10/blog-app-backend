package gr1.demo.blogapp.dto;

import gr1.demo.blogapp.model.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private String content;
    private String title;
    private String username;
    private List<Tag> listTag;

}
