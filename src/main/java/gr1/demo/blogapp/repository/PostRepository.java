package gr1.demo.blogapp.repository;

import gr1.demo.blogapp.model.Post;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAll(Pageable pageable);
    @Query(value = "select * from Post p where p.catelogy = ?1 ",nativeQuery = true)
    Page<Post> findPostByCatelogy(String catelogy, Pageable pageable);

    Post findPostById(Long id);
    @Override
    List<Post> findAll();
}
