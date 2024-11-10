package com.furidaweb.server.service.post;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostImageService {

    List<PostImage> getPostImagesByPost(Post post);

    void saveImage(MultipartFile file, Post post);

    void deletePostImagesByPost(Post post);

    void deleteAllPostImages();
}
