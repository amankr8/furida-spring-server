package com.furidaweb.server.service.post.impl;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import com.furidaweb.server.repository.PostImageRepository;
import com.furidaweb.server.service.CloudinaryService;
import com.furidaweb.server.service.post.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements PostImageService {

    private final PostImageRepository postImageRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<PostImage> getPostImagesByPost(Post post) {
        return postImageRepository.findByPost(post);
    }

    @Override
    public void saveImage(MultipartFile file, Post post) {
        Map<String, String> imageDetails = this.cloudinaryService.uploadFile(file, "furida/posts");
        PostImage postImage = PostImage.builder()
                .url(imageDetails.get("url"))
                .publicId(imageDetails.get("public_id"))
                .post(post)
                .build();

        postImageRepository.save(postImage);
    }

    @Override
    public void deletePostImagesByPost(Post post) {
        List<PostImage> postImages = postImageRepository.findByPost(post);

        for (PostImage postImage : postImages) {
            this.cloudinaryService.deleteFile(postImage.getPublicId());
            postImageRepository.deleteById(postImage.getId());
        }
    }

    @Override
    public void deleteAllPostImages() {
        List<PostImage> postImages = postImageRepository.findAll();

        this.cloudinaryService.deleteAllFilesInFolder("furida/posts");
        postImageRepository.deleteAll();
    }
}
