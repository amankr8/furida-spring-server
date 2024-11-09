package com.furidaweb.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);

    boolean deleteFile(String publicId);
}
