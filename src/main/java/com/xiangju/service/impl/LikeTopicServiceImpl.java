package com.xiangju.service.impl;

import com.xiangju.domain.LikeTopic;
import com.xiangju.mapper.LikeTopicMapper;
import com.xiangju.mapper.TopicMapper;
import com.xiangju.service.LikeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeTopicServiceImpl implements LikeTopicService {

    @Autowired
    LikeTopicMapper likeTopicMapper;

    @Autowired
    TopicMapper topicMapper;

    @Override
    public void giveLike(LikeTopic likeTopic) {
        likeTopicMapper.giveLike(likeTopic);
        topicMapper.addLike(likeTopic.getTopicid());
    }

    @Override
    public void removeLike(LikeTopic likeTopic) {
        likeTopicMapper.removeLike(likeTopic);
        topicMapper.deleteLike(likeTopic.getTopicid());
    }
}
