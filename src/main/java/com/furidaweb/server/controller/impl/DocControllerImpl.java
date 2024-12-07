package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.DocController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.dto.doc.DocRequestDto;
import com.furidaweb.server.dto.doc.DocResponseDto;
import com.furidaweb.server.service.doc.DocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DocControllerImpl implements DocController {

    @Autowired
    private final DocService docService;

    @Override
    public ResponseEntity<?> getAllDocuments() {
        List<DocResponseDto> docs = docService.getAllDocs();
        return ResponseEntity.ok(docs);
    }

    @Override
    public ResponseEntity<?> getDocumentsByProject(int projectId) {
        List<DocResponseDto> docs = docService.getDocsByProject(projectId);
        return ResponseEntity.ok(docs);
    }

    @Override
    public ResponseEntity<?> getDocument(int id) {
        DocResponseDto doc = docService.getDocById(id);
        return ResponseEntity.ok(doc);
    }

    @Override
    public ResponseEntity<?> createDocument(DocRequestDto doc) {
        DocResponseDto newDoc = docService.createDoc(doc);
        return ResponseEntity.ok(newDoc);
    }

    @Override
    public ResponseEntity<?> updateDocument(int id, DocRequestDto doc) {
        DocResponseDto updatedDoc = docService.updateDoc(id, doc);
        return ResponseEntity.ok(updatedDoc);
    }

    @Override
    public ResponseEntity<?> deleteDocument(int id) {
        docService.deleteDocById(id);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "Document deleted successfully!"));
    }

    @Override
    public ResponseEntity<?> deleteAllDocuments() {
        docService.deleteAllDocs();
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "All Documents deleted successfully!"));
    }
}
