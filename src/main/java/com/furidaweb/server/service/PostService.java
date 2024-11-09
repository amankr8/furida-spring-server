package com.furidaweb.server.service;

import com.furidaweb.server.dto.PostDto;
import com.furidaweb.server.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(int id);

    Post createPost(PostDto post) throws Exception;

    Post updatePost(int id, Post post);

    void deletePost(int id);

    void deleteAllPosts();
}
