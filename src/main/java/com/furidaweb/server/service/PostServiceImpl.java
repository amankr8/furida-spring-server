package com.furidaweb.server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.furidaweb.server.dto.PostDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final Cloudinary cloudinary;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    private String uploadFile(MultipartFile file) throws Exception {
        Map params = ObjectUtils.asMap();
        Map result = cloudinary.uploader().upload(file.getBytes(), params);

        return result.get("url").toString();
    }

    @Override
    public Post createPost(PostDto post) throws Exception {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setDate(new Date());

        if (post.getFile() != null) {
            newPost.setImgUrl(uploadFile(post.getFile()));
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
