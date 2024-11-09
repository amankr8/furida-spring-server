package com.furidaweb.server.service.impl;

import com.furidaweb.server.dto.PostDto;
import com.furidaweb.server.dto.PostResponseDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.PostRepository;
import com.furidaweb.server.service.PostImageService;
import com.furidaweb.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImageService postImageService;

    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::createPostResponseDto)
                .toList();
    }

    @Override
    public PostResponseDto getPostById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        return createPostResponseDto(post);
    }

    @Override
    public PostResponseDto createPost(PostDto post) {
        Post newPost = Post.builder()
                .date(new Date())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        Post savedPost = postRepository.save(newPost);
        if (post.getFile() != null) {
            this.postImageService.saveImage(post.getFile(), savedPost);
        }

        return createPostResponseDto(savedPost);
    }

    @Override
    public PostResponseDto updatePost(int id, Post post) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        updatePost.setDate(new Date());

        Post updatedPost = postRepository.save(updatePost);
        return createPostResponseDto(updatedPost);
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

    private PostResponseDto createPostResponseDto(Post post) {
        PostImage img = this.postImageService.getPostImageByPost(post);
        String imgUrl = img == null ? null : img.getUrl();

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imgUrl(imgUrl)
                .date(post.getDate())
                .build();
    }
}