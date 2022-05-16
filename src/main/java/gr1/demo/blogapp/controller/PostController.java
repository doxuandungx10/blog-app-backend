package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.PostDto;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/createPost")
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> showAllPost(){
        return ResponseEntity.ok(postService.showAllPost());
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getSinglePost(@PathVariable @RequestBody Long id){
        return ResponseEntity.ok(postService.getSinglePost(id));
    }
}
