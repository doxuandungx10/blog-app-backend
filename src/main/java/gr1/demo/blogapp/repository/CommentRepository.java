package gr1.demo.blogapp.repository;

import gr1.demo.blogapp.model.Comment;
import gr1.demo.blogapp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
//    List<Comment> findCommentByPostId(Long postId);
    Page<Comment> findCommentByObjPost(Post post, Pageable pageable);
}
