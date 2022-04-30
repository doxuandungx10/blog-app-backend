package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.PostDto;
import gr1.demo.blogapp.exception.PostNotFoundException;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUsername(principal.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
        return  ResponseEntity.ok(post);
    }

    public List<Post> showAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    public Post getSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id: "+id));
        return post;
    }
}

