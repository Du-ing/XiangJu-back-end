package com.xiangju.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private int topicid;
    private String userid;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date time;

    private String site;
    private String type;
    private int likes;
    private int comment;
    private int status;
}
