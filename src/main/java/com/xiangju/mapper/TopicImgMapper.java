package com.xiangju.mapper;

import com.xiangju.domain.TopicImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicImgMapper {
    void storeImg(TopicImg topicImg);

    List<String> getTopicImgs(int topicid);

    String getTopicHeadImg(int topicid);
}
