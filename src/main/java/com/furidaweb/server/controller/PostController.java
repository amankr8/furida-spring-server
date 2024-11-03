package com.furidaweb.server.controller;

import com.furidaweb.server.dto.CreatePostDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@ModelAttribute CreatePostDto post) throws IOException {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    @DeleteMapping
    public void deleteAllPosts() {
        postService.deleteAllPosts();
    }
}
