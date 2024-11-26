package com.furidaweb.server.service.doc;

import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocFileService {

    DocFile getDocFileByDocument(Document doc);

    void saveFile(MultipartFile file, Document doc);

    void deleteDocFileByDocument(Document doc);

    void deleteAllDocFiles();
}
