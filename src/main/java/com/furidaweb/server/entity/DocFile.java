package com.furidaweb.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docfile")
public class DocFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docfile_id")
    private int id;

    @Column(name = "docfile_public_id")
    private String publicId;

    @Column(name = "docfile_url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Document document;
}
