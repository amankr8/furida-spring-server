package com.furidaweb.server.service;

import com.furidaweb.server.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(int id);

    Post createPost(Post post);

    Post updatePost(int id, Post post);

    void deletePost(int id);

    void deleteAllPosts();
}
