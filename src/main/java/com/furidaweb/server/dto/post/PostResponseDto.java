package com.furidaweb.server.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private int id;
    private String title;
    private String content;
    private String imgUrl;
    private Date createDate;
}
