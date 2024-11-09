package com.furidaweb.server.service;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import org.springframework.web.multipart.MultipartFile;

public interface PostImageService {

    void saveImage(MultipartFile file, Post post);

    PostImage getPostImageByPost(Post post);
}
