package com.furidaweb.server.repository;

import com.furidaweb.server.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepository extends JpaRepository<Document, Integer> {
}
