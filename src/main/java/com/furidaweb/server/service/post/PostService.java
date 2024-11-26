package com.furidaweb.server.service.post;

import com.furidaweb.server.dto.post.PostRequestDto;
import com.furidaweb.server.dto.post.PostResponseDto;

import java.util.List;

public interface PostService {

    List<PostResponseDto> getAllPosts();

    PostResponseDto getPostById(int id);

    PostResponseDto createPost(PostRequestDto postDto) throws Exception;

    PostResponseDto updatePost(int id, PostRequestDto postDto);

    void deletePostById(int id);

    void deleteAllPosts();
}
