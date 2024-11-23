package com.furidaweb.server.service.post.impl;

import com.furidaweb.server.dto.post.PostRequestDto;
import com.furidaweb.server.dto.post.PostResponseDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.PostRepository;
import com.furidaweb.server.service.post.PostImageService;
import com.furidaweb.server.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
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
    public PostResponseDto createPost(PostRequestDto post) {
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
    public PostResponseDto updatePost(int id, PostRequestDto post) {
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
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        postImageService.deletePostImagesByPost(post);
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }

    private PostResponseDto createPostResponseDto(Post post) {
        List<PostImage> images = this.postImageService.getPostImagesByPost(post);
        List<String> imgUrls = new ArrayList<>();
        if (!images.isEmpty()) {
            imgUrls = images.stream().map(PostImage::getUrl).toList();
        } else {
            imgUrls.add(null);
        }

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imgUrl(imgUrls.get(0))
                .date(post.getDate())
                .build();
    }
}
