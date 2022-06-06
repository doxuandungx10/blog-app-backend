package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.CommentDto;
import gr1.demo.blogapp.model.Comment;
import gr1.demo.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<?> showAllCommentByPostID(@PathVariable @RequestBody Long postId,
                                                                @RequestParam(name ="page",required = false,defaultValue = "0")Integer page,
                                                                @RequestParam(name ="size", required = false,defaultValue = "2") Integer size,
                                                                @RequestParam(name ="sort", required = false, defaultValue = "ASC") String sort){

        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page,size,sortable);
        return ResponseEntity.ok(commentService.showAllComment(postId,pageable));
    }
    @PostMapping(value = "createComment")
    public Comment createComment(@RequestBody CommentDto commentDto) throws Exception {
        return commentService.createComment(commentDto);
    }
}
