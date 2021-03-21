package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentTopic {
    private int commentid;
    private int topicid;
    private String userid;
    private String content;
    private Date time;
    private int likes;
    private int replyid; //属于哪一个评论回复区
//    private String reply_userid; //回复的用户
}
