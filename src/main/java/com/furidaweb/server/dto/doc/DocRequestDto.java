package com.furidaweb.server.dto.doc;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String desc;

    private MultipartFile file;
}
