package com.furidaweb.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.furidaweb.server.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> uploadedFile = new HashMap<>();
        try {
            Map params = ObjectUtils.asMap();
            Map result = cloudinary.uploader().upload(file.getBytes(), params);

            uploadedFile.put("public_id", result.get("public_id").toString());
            uploadedFile.put("url", result.get("url").toString());

            return uploadedFile;
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
        return uploadedFile;
    }

    public boolean deleteFile(String publicId) {
        try {
            Map params = ObjectUtils.asMap();
            Map deleteResult = cloudinary.uploader().destroy(publicId, params);

            return deleteResult.get("result").equals("ok");
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
        return false;
    }
}
