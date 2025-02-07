package com.furidaweb.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;

    @Column(name = "p_title")
    private String title;

    @Column(name = "p_content", length = 510)
    private String content;

    @Column(name = "p_create_date")
    private Date createDate;
}
