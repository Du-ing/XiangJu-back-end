package com.xiangju.service;

import com.xiangju.domain.CommentTopic;

import java.util.List;
import java.util.Map;

public interface CommentTopicService {
    void addTopicComment(CommentTopic comment);

    List<Map> getTopicComments(int topicid);
}
