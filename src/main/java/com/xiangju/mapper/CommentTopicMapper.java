package com.xiangju.mapper;

import com.xiangju.domain.CommentTopic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentTopicMapper {
    void addComment(CommentTopic comment);

    List<CommentTopic> getTopicComments(int topic);

    List<CommentTopic> getTopicCommentReplys(int replayid);
}
