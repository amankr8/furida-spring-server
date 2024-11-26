package com.furidaweb.server.controller;

import com.furidaweb.server.dto.doc.DocRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/documents")
@Validated
public interface DocController {

    @GetMapping
    ResponseEntity<?> getAllDocuments();

    @GetMapping("/{id}")
    ResponseEntity<?> getDocument(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> createDocument(@Valid @ModelAttribute DocRequestDto doc) throws Exception;

    @PutMapping("/{id}")
    ResponseEntity<?> updateDocument(@PathVariable int id, @Valid @ModelAttribute DocRequestDto doc);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDocument(@PathVariable int id);

    @DeleteMapping
    ResponseEntity<?> deleteAllDocuments();
}
