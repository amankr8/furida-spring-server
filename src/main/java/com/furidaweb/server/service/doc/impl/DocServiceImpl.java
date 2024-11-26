package com.furidaweb.server.service.doc.impl;

import com.furidaweb.server.dto.doc.DocRequestDto;
import com.furidaweb.server.dto.doc.DocResponseDto;
import com.furidaweb.server.service.doc.DocService;

import java.util.List;

public class DocServiceImpl implements DocService {

    @Override
    public List<DocResponseDto> getAllDocs() {
        return List.of();
    }

    @Override
    public DocResponseDto getDocById(int id) {
        return null;
    }

    @Override
    public DocResponseDto createDoc(DocRequestDto post) throws Exception {
        return null;
    }

    @Override
    public DocResponseDto updateDoc(int id, DocRequestDto post) {
        return null;
    }

    @Override
    public void deleteDocById(int id) {

    }

    @Override
    public void deleteAllDocs() {

    }
}
