package com.xiangju.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Help {
    private int helpid;
    private String userid;
    private String title;
    private String site;
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date startime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endtime;

    private double reward;
    private String content;
    private int status;
}
