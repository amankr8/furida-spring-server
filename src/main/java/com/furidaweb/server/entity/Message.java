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
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private int id;

    @Column(name = "m_name")
    private String name;

    @Column(name = "m_email")
    private String email;

    @Column(name = "m_msg", length = 510)
    private String msg;

    @Column(name = "m_send_date")
    private Date sendDate;

    @Column(name = "m_archive")
    private Boolean archive;
}
