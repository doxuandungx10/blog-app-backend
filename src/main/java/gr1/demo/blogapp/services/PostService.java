package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.PostDto;
import gr1.demo.blogapp.exception.PostNotFoundException;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        post.setCatelogy(postDto.getCatelogy());
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUsername(principal.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
        return  ResponseEntity.ok(post);
    }

    public ResponseEntity<?> showAllPost(Pageable pageable) {
        return ResponseEntity.ok(postRepository.findAll(pageable));
    }

    public Post getSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id: "+id));
        return post;
    }
    public ResponseEntity<?> getPostByCatelogy(String catelogy,Pageable pageable){
        return ResponseEntity.ok(postRepository.findPostByCatelogy(catelogy,pageable));
    }
}

