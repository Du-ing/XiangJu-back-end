package com.xiangju.service;

import com.xiangju.domain.LikeTopic;

import java.util.List;
import java.util.Map;

public interface LikeTopicService {
    void giveLike(LikeTopic likeTopic);

    void removeLike(LikeTopic likeTopic);

    List<Map> getUserLikeTopics(String userid);
}
