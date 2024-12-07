package com.furidaweb.server.service.doc;

import com.furidaweb.server.dto.doc.DocRequestDto;
import com.furidaweb.server.dto.doc.DocResponseDto;

import java.util.List;

public interface DocService {

    List<DocResponseDto> getAllDocs();

    List<DocResponseDto> getDocsByProject(int projectId);

    DocResponseDto getDocById(int id);

    DocResponseDto createDoc(DocRequestDto docDto);

    DocResponseDto updateDoc(int id, DocRequestDto docDto);

    void deleteDocById(int id);

    void deleteAllDocs();
}
