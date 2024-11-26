package com.furidaweb.server.service.doc;

import com.furidaweb.server.entity.Document;
import com.furidaweb.server.entity.Post;
import com.furidaweb.server.entity.PostImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocFileService {

    List<PostImage> getDocFilesByPost(Document doc);

    void saveFile(MultipartFile file, Document doc);

    void deleteDocFilesByPost(Document doc);

    void deleteAllDocFiles();
}
