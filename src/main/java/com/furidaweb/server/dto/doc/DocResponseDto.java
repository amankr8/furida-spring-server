package com.furidaweb.server.dto.doc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocResponseDto {

    private int id;
    private String name;
    private String desc;
    private String docUrl;
    private Date createDate;
    private int projectId;
}
