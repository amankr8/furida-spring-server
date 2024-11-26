package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.dto.doc.DocRequestDto;
import com.furidaweb.server.dto.doc.DocResponseDto;
import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.DocRepository;
import com.furidaweb.server.service.doc.DocFileService;
import com.furidaweb.server.service.doc.DocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private final DocRepository docRepository;
    @Autowired
    private final DocFileService docFileService;

    @Override
    public List<DocResponseDto> getAllDocs() {
        return docRepository.findAll()
                .stream().map(this::createDocResponseDto).toList();
    }

    @Override
    public DocResponseDto getDocById(int id) {
        Document doc = docRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        return createDocResponseDto(doc);
    }

    @Override
    public DocResponseDto createDoc(DocRequestDto docDto) throws Exception {
        Document newDoc = Document.builder()
                .name(docDto.getName())
                .desc(docDto.getDesc())
                .build();

        Document savedDoc = docRepository.save(newDoc);
        this.docFileService.saveFile(docDto.getFile(), savedDoc);

        return createDocResponseDto(savedDoc);
    }

    @Override
    public DocResponseDto updateDoc(int id, DocRequestDto docDto) {
        Document doc = docRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        doc.setName(docDto.getName());
        doc.setDesc(docDto.getDesc());

        return createDocResponseDto(docRepository.save(doc));
    }

    @Override
    public void deleteDocById(int id) {
        Document doc = docRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        docFileService.deleteDocFileByDocument(doc);
        docRepository.deleteById(id);
    }

    @Override
    public void deleteAllDocs() {
        docFileService.deleteAllDocFiles();
        docRepository.deleteAll();
    }

    private DocResponseDto createDocResponseDto(Document doc) {
        DocFile docFile = docFileService.getDocFileByDocument(doc);

        return DocResponseDto.builder()
                .name(doc.getName())
                .desc(doc.getDesc())
                .docUrl(docFile.getUrl())
                .build();
    }
}
