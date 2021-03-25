package com.xiangju.service.impl;

import com.xiangju.domain.LikeTopic;
import com.xiangju.domain.Topic;
import com.xiangju.mapper.LikeTopicMapper;
import com.xiangju.mapper.TopicImgMapper;
import com.xiangju.mapper.TopicMapper;
import com.xiangju.service.LikeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeTopicServiceImpl implements LikeTopicService {

    @Autowired
    LikeTopicMapper likeTopicMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TopicImgMapper topicImgMapper;

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

    @Override
    public List<Map> getUserLikeTopics(String userid) {
        List<LikeTopic> likeTopics = likeTopicMapper.getUserLikeTopics(userid);
        List<Map> res = new ArrayList<>();
        for (LikeTopic likeTopic : likeTopics) {
            Topic topic = topicMapper.getTopicById(likeTopic.getTopicid());
            Map<String,Object> map = new HashMap<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            String time = sdf.format(topic.getTime());
            map.put("time",time);

            map.put("topicid",topic.getTopicid());
            map.put("title",topic.getTitle());
            map.put("site",topic.getSite());
            map.put("status",topic.getStatus());
            //获取话题封面图片
            map.put("image",topicImgMapper.getTopicHeadImg(topic.getTopicid()));

            res.add(map);
        }
        return res;
    }
}
