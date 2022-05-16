package gr1.demo.blogapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name ="createdOn")
    private Instant createOn;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "postId")
    private Long postId;
}
