package com.furidaweb.server.repository;

import com.furidaweb.server.entity.DocFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocFileRepository extends JpaRepository<DocFile, Integer> {
}
