package gr1.demo.blogapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Post {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    @NotEmpty
    private String content;


    @Column(name = "createdOn")
    private Instant createdOn;

    @Column(name = "updatedOn")
    private Instant updateOn;

    @Column(name = "username")
    @NotBlank
    private String username;

    @OneToMany(mappedBy = "objPost")
    private List<Comment> listComment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="post_has_tag",
            joinColumns = @JoinColumn(name ="post_id"),
            inverseJoinColumns= @JoinColumn(name ="tag_id"))
    private List<Tag> listTag;


}
