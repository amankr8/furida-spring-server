package com.furidaweb.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryService {

    Map<String, String> uploadFile(MultipartFile file);

    boolean deleteFile(String publicId);
}
