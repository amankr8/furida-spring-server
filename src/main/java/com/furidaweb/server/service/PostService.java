package com.furidaweb.server.service;

import com.furidaweb.server.dto.CreatePostDto;
import com.furidaweb.server.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(int id);

    Post createPost(CreatePostDto post) throws IOException;

    Post updatePost(int id, Post post);

    void deletePost(int id) throws IOException;

    void deleteAllPosts() throws IOException;
}
