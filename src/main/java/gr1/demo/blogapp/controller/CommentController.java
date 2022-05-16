package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.CommentDto;
import gr1.demo.blogapp.model.Comment;
import gr1.demo.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping(value = "/showAllComment/{postId}")
    public ResponseEntity<List<Comment>> showAllCommentByPostID(@PathVariable @RequestBody Long postId){
        return ResponseEntity.ok(commentService.showAllComment(postId));
    }
    @PostMapping(value = "createComment")
    public Comment createComment(@RequestBody CommentDto commentDto) throws Exception {
        return commentService.createComment(commentDto);
    }
}
