package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.PostDto;
import gr1.demo.blogapp.dto.TagDto;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.model.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import gr1.demo.blogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.DataTruncation;
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
    @GetMapping("/allPostNotPaging")
    public ResponseEntity<?> showAllPostsNotPaging(){
        return postService.showAllPostNotPaging();
    }
    @GetMapping("/all")
    public ResponseEntity<?> showAllPost(@RequestParam(name ="page",required = false,defaultValue = "0")Integer page,
                                         @RequestParam(name ="size", required = false,defaultValue = "5") Integer size,
                                         @RequestParam(name ="sort", required = false, defaultValue = "DESC") String sort){
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page,size,sortable);
        return postService.showAllPost(pageable);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getSinglePost(@PathVariable @RequestBody Long id){
        return ResponseEntity.ok(postService.getSinglePost(id));
    }
    @GetMapping("/getRelatedPost/{post_id}")
    public  ResponseEntity<?> getRelatedPost(@PathVariable Long post_id){

        return ResponseEntity.ok(postService.getRelatedPost(postService.getSinglePost(post_id).getListTag(),post_id));
    }
    @GetMapping("/getPostByTag/{tag_id}")
    public ResponseEntity<?> getPostByTag2(@PathVariable Long tag_id){
        return ResponseEntity.ok(postService.getPostByTag(tag_id));
    }
    @GetMapping("/findPostByTitle")
    public ResponseEntity<?> findPostByTitle(@RequestParam(name ="page",required = false,defaultValue = "0")Integer page,
                                             @RequestParam(name ="size", required = false,defaultValue = "5") Integer size,
                                             @RequestParam(name ="sort", required = false, defaultValue = "DESC") String sort,
                                             @RequestParam(name ="title", required = false, defaultValue = "DESC") String title
                                             )
    {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page,size,sortable);
        return ResponseEntity.ok(postService.findPostByTitle(pageable,"%"+title+"%"));
    }


}
