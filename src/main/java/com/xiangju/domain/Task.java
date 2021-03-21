package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int helpid;//求助id
    private String userid;//接受任务用户的id
    private int status;//状态
    private int topicid;//发布的作品id
}