package com.furidaweb.server.repository;

import com.furidaweb.server.entity.Document;
import com.furidaweb.server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocRepository extends JpaRepository<Document, Integer> {

    List<Document> findByProject(Project project);
}
