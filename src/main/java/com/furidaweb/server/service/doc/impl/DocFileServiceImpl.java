package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.DocFileRepository;
import com.furidaweb.server.service.CloudinaryService;
import com.furidaweb.server.service.doc.DocFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class DocFileServiceImpl implements DocFileService {

    @Autowired
    private final DocFileRepository docFileRepository;

    @Autowired
    private final CloudinaryService cloudinaryService;

    @Value("${app.name}")
    private String APP_NAME;

    private String getFolderPath() {
        return APP_NAME.concat("/documents");
    }

    @Override
    public DocFile getDocFileByDocument(Document doc) {
        return docFileRepository.findByDocument(doc)
                .orElseThrow(() -> new ResourceNotFoundException("File for the document not found"));
    }

    @Override
    public void saveFile(MultipartFile file, Document doc) {
        Map<String, String> fileDetails = cloudinaryService.uploadFile(file, getFolderPath());
        DocFile docFile = DocFile.builder()
                .publicId(fileDetails.get("public_id"))
                .url(fileDetails.get("url"))
                .document(doc)
                .build();

        docFileRepository.save(docFile);
    }

    @Override
    public void deleteDocFileByDocument(Document doc) {
        DocFile docFile = docFileRepository.findByDocument(doc)
                .orElseThrow(() -> new ResourceNotFoundException("File for the document not found"));

        cloudinaryService.deleteFile(docFile.getPublicId());
        docFileRepository.deleteById(doc.getId());
    }

    @Override
    public void deleteAllDocFiles() {
        cloudinaryService.deleteAllFilesInFolder(getFolderPath());
        docFileRepository.deleteAll();
    }
}
