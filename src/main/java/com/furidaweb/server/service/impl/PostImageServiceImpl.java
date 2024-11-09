package com.furidaweb.server.service.impl;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import com.furidaweb.server.repository.PostImageRepository;
import com.furidaweb.server.service.CloudinaryService;
import com.furidaweb.server.service.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements PostImageService {

    private final PostImageRepository postImageRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public PostImage getPostImageByPost(Post post) {
        return postImageRepository.findByPost(post).orElse(null);
    }

    @Override
    public void saveImage(MultipartFile file, Post post) {
        Map<String, String> imageDetails = this.cloudinaryService.uploadFile(file);
        PostImage postImage = PostImage.builder()
                .url(imageDetails.get("url"))
                .publicId(imageDetails.get("public_id"))
                .post(post)
                .build();

        postImageRepository.save(postImage);
    }
}
