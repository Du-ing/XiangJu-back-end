package com.xiangju.mapper;

import com.xiangju.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicMapper {
    List<Topic> getAllTopic();

    List<Topic> getTopicByType(String type);

    Topic getTopicById(int topicid);

    void editTopic(Topic topic);

    void addTopic(Topic topic);

    List<Topic> getUserTopics(String userid);

    void addLike(int topicid);

    void deleteLike(int topicid);

    //模糊搜索(标题)
    List<Topic> searchTopics(String key);

    void deleteTopic(@Param("topicid") int topicid, @Param("userid") String userid);
}
