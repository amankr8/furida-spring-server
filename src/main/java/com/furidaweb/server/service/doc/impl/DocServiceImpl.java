package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.dto.doc.DocRequestDto;
import com.furidaweb.server.dto.doc.DocResponseDto;
import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import com.furidaweb.server.entity.Project;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.DocRepository;
import com.furidaweb.server.repository.ProjectRepository;
import com.furidaweb.server.service.doc.DocFileService;
import com.furidaweb.server.service.doc.DocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private final DocRepository docRepository;

    @Autowired
    private final DocFileService docFileService;

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public List<DocResponseDto> getAllDocs() {
        return docRepository.findAll()
                .stream()
                .sorted((d1, d2) -> d2.getCreateDate().compareTo(d1.getCreateDate()))
                .map(this::createDocResponseDto).toList();
    }

    @Override
    public List<DocResponseDto> getDocsByProject(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("No project with id exists"));

        List<Document> docs = docRepository.findByProject(project);
        return docs.stream()
                .sorted((d1, d2) -> d2.getCreateDate().compareTo(d1.getCreateDate()))
                .map(this::createDocResponseDto).toList();
    }

    @Override
    public DocResponseDto getDocById(int id) {
        Document doc = docRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        return createDocResponseDto(doc);
    }

    @Override
    public DocResponseDto createDoc(DocRequestDto docDto) {
        Project project = projectRepository.findById(docDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("No project with id exists"));

        Document newDoc = Document.builder()
                .name(docDto.getName())
                .desc(docDto.getDesc())
                .project(project)
                .createDate(new Date())
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
                .id(doc.getId())
                .name(doc.getName())
                .desc(doc.getDesc())
                .docUrl(docFile.getUrl())
                .projectId(doc.getProject().getId())
                .createDate(doc.getCreateDate())
                .build();
    }
}
