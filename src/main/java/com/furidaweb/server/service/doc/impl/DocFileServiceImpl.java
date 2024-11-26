package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.entity.Document;
import com.furidaweb.server.entity.PostImage;
import com.furidaweb.server.service.doc.DocFileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DocFileServiceImpl implements DocFileService {
    @Override
    public List<PostImage> getDocFilesByPost(Document doc) {
        return List.of();
    }

    @Override
    public void saveFile(MultipartFile file, Document doc) {

    }

    @Override
    public void deleteDocFilesByPost(Document doc) {

    }

    @Override
    public void deleteAllDocFiles() {

    }
}
