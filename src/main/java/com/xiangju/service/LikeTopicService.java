package com.xiangju.service;

import com.xiangju.domain.LikeTopic;

public interface LikeTopicService {
    void giveLike(LikeTopic likeTopic);

    void removeLike(LikeTopic likeTopic);
}
