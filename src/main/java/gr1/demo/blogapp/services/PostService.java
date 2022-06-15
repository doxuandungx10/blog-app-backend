package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.PostDto;
import gr1.demo.blogapp.dto.TagDto;
import gr1.demo.blogapp.exception.PostNotFoundException;
import gr1.demo.blogapp.model.Post;
import gr1.demo.blogapp.model.Tag;
import gr1.demo.blogapp.repository.PostRepository;
import gr1.demo.blogapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;

    public ResponseEntity createPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postRepository.count()+1);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setListTag(postDto.getListTag());
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUsername(principal.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
        return  ResponseEntity.ok(post);
    }

    public ResponseEntity<?> showAllPost(Pageable pageable) {
        return ResponseEntity.ok(postRepository.findAll(pageable));
    }
    public ResponseEntity<?> showAllPostNotPaging(){
        return ResponseEntity.ok(postRepository.findAll());
    }

    public Post getSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id: "+id));
        return post;
    }
    public List<List<Map<Post,Object>>> getPostByTag(List<Tag> tags,Long post_id) {
        List<List<Map<Post, Object>>> postDtoList = new ArrayList<>();
        int count = 0;
        for (Tag tag : tags) {
            count++;
            postDtoList.add(tagRepository.findPostByTagName(tag.getName(),post_id));
            if (count == 5) break;
        }
        return postDtoList;
    }
    public List<Map<Post,Object>> getPostByTag2(Long id){
        return postRepository.findPostByTag(id);
    }
    public ResponseEntity<?> findPostByTitle(Pageable pageable,String title){
        return ResponseEntity.ok(postRepository.findPostByTitle(title,pageable));
    }
}

