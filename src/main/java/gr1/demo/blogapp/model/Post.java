package gr1.demo.blogapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank
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


}
