package com.xiangju.mapper;

import com.xiangju.domain.LikeTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LikeTopicMapper {
    void giveLike(LikeTopic likeTopic);

    void removeLike(LikeTopic likeTopic);

    LikeTopic getLikeStatus(@Param("userid") String userid, @Param("topicid") int topicid);

    List<LikeTopic> getUserLikeTopics(String userid);
}
