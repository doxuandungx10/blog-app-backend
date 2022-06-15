package gr1.demo.blogapp.repository;

import gr1.demo.blogapp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAll(Pageable pageable);
    @Query(value = "select * from Post p where p.id = ?1 ",nativeQuery = true)
    Post findPostById(Long post_id);

    @Query(value = "select p.* from Post p,post_has_tag pt, tag t where p.id = pt.post_id and pt.tag_id = t.id and t.id = ?1",nativeQuery = true)
    List<Map<Post,Object>> findPostByTag(Long tag_id);
    @Override
    List<Post> findAll();
}
