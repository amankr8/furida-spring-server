package com.furidaweb.server.controller;

import com.furidaweb.server.dto.post.PostRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@Validated
public interface PostController {

    @GetMapping
    ResponseEntity<?> getAllPosts();

    @GetMapping("/{id}")
    ResponseEntity<?> getPost(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> createPost(@Valid @ModelAttribute PostRequestDto post) throws Exception;

    @PutMapping("/{id}")
    ResponseEntity<?> updatePost(@PathVariable int id, @Valid @ModelAttribute PostRequestDto post);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePost(@PathVariable int id);

    @DeleteMapping
    ResponseEntity<?> deleteAllPosts();
}
