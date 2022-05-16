package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.CommentDto;
import gr1.demo.blogapp.model.Comment;
import gr1.demo.blogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> showAllComment(Long postId){
        return commentRepository.findCommentByPostId(postId);
    }
    public Comment createComment(CommentDto commentDto) throws Exception{
        Comment comment =  new Comment();
        if(commentDto.getComment().isEmpty()) throw new Exception("Comment is empty");
        comment.setComment(commentDto.getComment());
        comment.setUsername(commentDto.getUsername());
        comment.setPostId(commentDto.getPostId());
        comment.setCreateOn(Instant.now());
//        commentRepository.save(comment);
        return commentRepository.save(comment);
    }
}
