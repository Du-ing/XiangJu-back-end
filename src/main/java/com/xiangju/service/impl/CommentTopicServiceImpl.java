package com.xiangju.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.CommentTopic;
import com.xiangju.domain.User;
import com.xiangju.mapper.CommentTopicMapper;
import com.xiangju.mapper.UserMapper;
import com.xiangju.service.CommentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentTopicServiceImpl implements CommentTopicService {

    @Autowired
    CommentTopicMapper commentTopicMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void addTopicComment(CommentTopic comment) {
        commentTopicMapper.addComment(comment);
    }

    @Override
    public List<Map> getTopicComments(int topicid) {
        List<Map> res = new ArrayList<>();
        List<CommentTopic> comments = commentTopicMapper.getTopicComments(topicid);
        for (CommentTopic comment : comments) {
            Map map = JSONObject.parseObject(JSONObject.toJSONString(comment),Map.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(comment.getTime());
            map.replace("time",time);

            User user = userMapper.getUserById(comment.getUserid());
            map.put("username",user.getUsername());
            map.put("headimg",user.getHeadimg());

            //获取每一条评论的回复区
            List<Map> replys_map = new ArrayList<>();
            int replyid = comment.getReplyid();
            List<CommentTopic> replys = commentTopicMapper.getTopicCommentReplys(replyid);

            for (CommentTopic reply : replys) {
                Map reply_map = JSONObject.parseObject(JSONObject.toJSONString(reply),Map.class);
                String time1 = sdf.format(comment.getTime());
                reply_map.replace("time",time1);

                User user_rep = userMapper.getUserById(reply.getUserid());
                reply_map.put("username",user_rep.getUsername());
                reply_map.put("headimg",user_rep.getHeadimg());
                replys_map.add(reply_map);
            }

            map.put("replys",replys_map);
            res.add(map);
        }
        return res;
    }
}
