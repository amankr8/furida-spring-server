package com.furidaweb.server.repository;

import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer> {

    List<PostImage> findByPost(Post post);
}
