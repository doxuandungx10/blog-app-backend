package gr1.demo.blogapp.repository;

import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "select p.* from tag t,post p,post_has_tag pt where t.id =pt.tag_id and pt.post_id = p.id and t.name =?1 and p.id not like ?2 ",nativeQuery = true)
   List<Map<Post,Object>> findPostByTagName(String tag_name,Long post_id);
}
