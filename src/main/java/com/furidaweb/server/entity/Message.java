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
    @Column(name = "message_id")
    private int id;

    @Column(name = "message_name")
    private String name;

    @Column(name = "message_email")
    private String email;

    @Column(name = "message_msg", length = 510)
    private String msg;

    @Column(name = "message_date")
    private Date date;

    @Column(name = "message_read")
    private Boolean read;
}
