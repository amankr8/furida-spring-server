package com.furidaweb.server.repository;

import com.furidaweb.server.entity.DocFile;
import com.furidaweb.server.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocFileRepository extends JpaRepository<DocFile, Integer> {

    Optional<DocFile> findByDocument(Document doc);
}
