package gr1.demo.blogapp.repository;

import gr1.demo.blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByPostId(Long postId);
}
