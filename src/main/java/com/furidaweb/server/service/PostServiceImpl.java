package com.furidaweb.server.service;

import com.furidaweb.server.dto.CreatePostDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    private String saveImage(MultipartFile file) throws IOException {
        String uploadDir = "uploads/posts/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        Files.createDirectories(filePath.getParent()); // Create directories if they don’t exist
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    @Override
    public Post createPost(CreatePostDto post) throws IOException {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setDate(new Date());

        if (post.getFile() != null) {
            newPost.setImgPath(saveImage(post.getFile()));
        }
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(int id, Post post) {
        Post updatedPost = postRepository.findById(id).orElse(null);
        if(updatedPost == null) {
            return null;
        } else {
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            updatedPost.setDate(new Date());
            return postRepository.save(updatedPost);
        }
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }
}
