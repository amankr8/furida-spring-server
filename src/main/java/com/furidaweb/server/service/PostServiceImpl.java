package com.furidaweb.server.service;

import com.furidaweb.server.dto.CreatePostDto;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.exception.ResourceNotFoundException;
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
import java.util.Objects;
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
        String baseFilePath = "uploads/posts/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(baseFilePath + fileName);

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
        Post updatedPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        updatedPost.setTitle(updatedPost.getTitle());
        updatedPost.setContent(updatedPost.getContent());
        updatedPost.setDate(new Date());
        return postRepository.save(updatedPost);
    }

    @Override
    public void deletePost(int id) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Delete the associated image file if it exists
        String imgPath = post.getImgPath();
        if (imgPath != null) {
            Path filePath = Paths.get(imgPath);
            Files.deleteIfExists(filePath);
            System.out.println("Deleted image: " + filePath);
        }

        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPosts() throws IOException {
        List<Post> allPosts = postRepository.findAll();
        List<String> imgPaths = allPosts.stream()
                .map(Post::getImgPath).filter(Objects::nonNull).toList();

        for (String imgPath : imgPaths) {
            // Delete image file if it exists
            Path filePath = Paths.get(imgPath);
            Files.deleteIfExists(filePath);
        }

        postRepository.deleteAll();
    }
}
