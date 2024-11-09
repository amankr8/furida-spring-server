package com.furidaweb.server.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${fileStorage.name}")
    private String UPLOAD_URL;

    @Value("${fileStorage.api.key}")
    private String PUBLIC_KEY;

    @Value("${fileStorage.api.secret}")
    private String PRIVATE_KEY;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", UPLOAD_URL,
                "api_key", PUBLIC_KEY,
                "api_secret", PRIVATE_KEY));
    }
}
