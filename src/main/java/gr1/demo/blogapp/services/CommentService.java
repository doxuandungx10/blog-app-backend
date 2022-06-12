package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.CommentDto;
import gr1.demo.blogapp.model.Comment;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.repository.CommentRepository;
import gr1.demo.blogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    public Page<Comment> showAllComment(Long postId, Pageable pageable){
        return commentRepository.findCommentByObjPost(postRepository.findPostById(postId), pageable);
    }

    public Comment createComment(CommentDto commentDto) throws Exception{
        Comment comment =  new Comment();
        comment.setId(commentRepository.count()+1);
        comment.setObjPost(postRepository.findPostById(commentDto.getPostId()));
        if(commentDto.getComment().isEmpty()) throw new Exception("Comment is empty");
        comment.setComment(commentDto.getComment());
        comment.setUsername(commentDto.getUsername());

        comment.setCreateOn(Instant.now());
//        commentRepository.save(comment);
        return commentRepository.save(comment);
    }
}
