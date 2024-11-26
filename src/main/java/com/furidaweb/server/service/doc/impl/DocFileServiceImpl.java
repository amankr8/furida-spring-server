package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.DocFileRepository;
import com.furidaweb.server.service.CloudinaryService;
import com.furidaweb.server.service.doc.DocFileService;
import com.furidaweb.server.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final String FOLDER_PATH = AppConstants.APP_NAME + "/documents";

    @Override
    public DocFile getDocFileByDocument(Document doc) {
        return docFileRepository.findByDocument(doc)
                .orElseThrow(() -> new ResourceNotFoundException("File for the document not found"));
    }

    @Override
    public void saveFile(MultipartFile file, Document doc) {
        Map<String, String> fileDetails = cloudinaryService.uploadFile(file, FOLDER_PATH);
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
        cloudinaryService.deleteAllFilesInFolder(FOLDER_PATH);
        docFileRepository.deleteAll();
    }
}
