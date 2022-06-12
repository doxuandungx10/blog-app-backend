package gr1.demo.blogapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name ="createdOn")
    private Instant createOn;

    @Column(name = "username")
    @NotBlank
    private String username;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id",nullable=false)
    private Post objPost;
}
