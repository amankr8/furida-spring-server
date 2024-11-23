package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.PostController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.dto.post.PostRequestDto;
import com.furidaweb.server.dto.post.PostResponseDto;
import com.furidaweb.server.exception.ResourceNotFoundException;
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
        try {
            List<PostResponseDto> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getPost(int id) {
        try {
            PostResponseDto post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StatusResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> createPost(PostRequestDto post) {
        try {
            PostResponseDto newPost = postService.createPost(post);
            return ResponseEntity.ok(newPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updatePost(int id, PostRequestDto post) {
        try {
            PostResponseDto updatedPost = postService.updatePost(id, post);
            return ResponseEntity.ok(updatedPost);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StatusResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deletePost(int id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "Post deleted successfully!"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StatusResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteAllPosts() {
        try {
            postService.deleteAllPosts();
            return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "All Posts deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
