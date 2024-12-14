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
@Table(name = "document_file")
public class DocFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "df_id")
    private int id;

    @Column(name = "df_public_id")
    private String publicId;

    @Column(name = "df_url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_id")
    private Document document;
}
