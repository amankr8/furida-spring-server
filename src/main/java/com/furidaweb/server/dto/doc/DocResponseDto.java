package com.furidaweb.server.dto.doc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocResponseDto {

    private int id;
    private String name;
    private String desc;
    private String docUrl;
    private int projectId;
}
