package com.furidaweb.server.repository;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostImageRepository extends JpaRepository<PostImage, Integer> {

    Optional<PostImage> findByPost(Post post);
}
