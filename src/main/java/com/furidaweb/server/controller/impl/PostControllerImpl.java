package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.PostController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.dto.post.PostRequestDto;
import com.furidaweb.server.dto.post.PostResponseDto;
import com.furidaweb.server.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostControllerImpl implements PostController {

    @Autowired
    private final PostService postService;

    @Override
    public ResponseEntity<?> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Override
    public ResponseEntity<?> getPost(int id) {
        PostResponseDto post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @Override
    public ResponseEntity<?> createPost(PostRequestDto post) throws Exception {
        PostResponseDto newPost = postService.createPost(post);
        return ResponseEntity.ok(newPost);
    }

    @Override
    public ResponseEntity<?> updatePost(int id, PostRequestDto post) {
        PostResponseDto updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }

    @Override
    public ResponseEntity<?> deletePost(int id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "Post deleted successfully!"));
    }

    @Override
    public ResponseEntity<?> deleteAllPosts() {
        postService.deleteAllPosts();
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "All Posts deleted successfully!"));
    }
}
