package com.furidaweb.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pj_id")
    private int id;

    @Column(name = "pj_name")
    private String name;

    @Column(name = "pj_desc")
    private String desc;

    @Column(name = "pj_address")
    private String address;

    @Column(name = "pj_create_date")
    private Date createDate;
}
