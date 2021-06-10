package com.xiangju.service;

import com.xiangju.domain.Topic;
import com.xiangju.domain.TopicImg;

import java.util.List;
import java.util.Map;

public interface TopicService {
    List<Topic> getAllTopic();

    List<Topic> getPassTopic();

    Topic getTopicById(int topicid);

    void editTopic(Topic topic);

    int addTopic(Topic topic);

    void storeTopicImg(TopicImg topicImg);

    List<String> getTopicImgs(int topicid);

    List<Map> getUserTopics(String userid);

    Map getTopicDetail(int topicid, String usernow_id);

    void deleteTopic(int topicid, String userid);

    List<Topic> searchTopicsByManage(String key);
}
