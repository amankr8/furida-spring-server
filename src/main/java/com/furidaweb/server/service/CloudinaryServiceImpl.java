package com.furidaweb.server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private final Cloudinary cloudinary;

    @Override
    public Map<String, String> uploadFile(MultipartFile file, String folderPath) {
        Map<String, String> uploadedFile = new HashMap<>();
        try {
            Map<?, ?> params = ObjectUtils.asMap(
                    "folder", folderPath
            );
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), params);

            uploadedFile.put("public_id", result.get("public_id").toString());
            uploadedFile.put("url", result.get("url").toString());

            return uploadedFile;
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
        return uploadedFile;
    }

    @Override
    public void deleteFile(String publicId) {
        try {
            Map<?, ?> params = ObjectUtils.asMap();
            Map<?, ?> deleteResult = cloudinary.uploader().destroy(publicId, params);

            if (!deleteResult.get("result").equals("ok")) {
                System.out.println("Could not delete file: " + deleteResult.get("result"));
            }
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllFilesInFolder(String folderPath) {
        try {
            Map<?, ?> params = ObjectUtils.asMap();
            Map<?, ?> deleteResult = cloudinary.api().deleteResourcesByPrefix(folderPath, params);

            if (!deleteResult.get("result").equals("ok")) {
                System.out.println("Could not delete files: " + deleteResult.get("result"));
            }
        } catch (Exception e) {
            System.out.println("Error deleting files: " + e.getMessage());
        }
    }
}
