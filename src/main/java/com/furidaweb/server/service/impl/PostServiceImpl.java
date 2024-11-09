package com.furidaweb.server.service.impl;

import com.furidaweb.server.dto.PostDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.PostRepository;
import com.furidaweb.server.service.CloudinaryService;
import com.furidaweb.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post createPost(PostDto post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setDate(new Date());

        if (post.getFile() != null) {
            newPost.setImgUrl(this.cloudinaryService.uploadFile(post.getFile()));
        }
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(int id, Post post) {
        Post updatedPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        updatedPost.setTitle(updatedPost.getTitle());
        updatedPost.setContent(updatedPost.getContent());
        updatedPost.setDate(new Date());
        return postRepository.save(updatedPost);
    }

    @Override
    public void deletePost(int id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }
}
