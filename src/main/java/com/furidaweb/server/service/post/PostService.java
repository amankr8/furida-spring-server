package com.furidaweb.server.service.post;

import com.furidaweb.server.dto.post.PostRequestDto;
import com.furidaweb.server.dto.post.PostResponseDto;
import com.furidaweb.server.entity.Post;

import java.util.List;

public interface PostService {

    List<PostResponseDto> getAllPosts();

    PostResponseDto getPostById(int id);

    PostResponseDto createPost(PostRequestDto post) throws Exception;

    PostResponseDto updatePost(int id, Post post);

    void deletePost(int id);

    void deleteAllPosts();
}
