package com.furidaweb.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.furidaweb.server.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            Map params = ObjectUtils.asMap();
            Map result = cloudinary.uploader().upload(file.getBytes(), params);

            return result.get("url").toString();
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
        return null;
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
