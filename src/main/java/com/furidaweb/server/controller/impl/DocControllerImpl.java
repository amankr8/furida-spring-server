package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.DocController;
import com.furidaweb.server.dto.doc.DocRequestDto;
import org.springframework.http.ResponseEntity;

public class DocControllerImpl implements DocController {

    @Override
    public ResponseEntity<?> getAllDocuments() {
        return null;
    }

    @Override
    public ResponseEntity<?> getDocument(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createDocument(DocRequestDto post) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateDocument(int id, DocRequestDto post) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteDocument(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAllDocuments() {
        return null;
    }
}
