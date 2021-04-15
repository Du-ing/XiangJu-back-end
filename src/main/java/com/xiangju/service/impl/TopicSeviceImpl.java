package com.xiangju.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.*;
import com.xiangju.mapper.*;
import com.xiangju.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicSeviceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TopicImgMapper topicImgMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikeTopicMapper likeTopicMapper;
    @Autowired
    FansMapper fansMapper;

    @Override
    public List<Topic> getAllTopic() {
        List<Topic> topics = topicMapper.getAllTopic();
        return topics;
    }

    @Override
    public Topic getTopicById(int topicid) {
        return topicMapper.getTopicById(topicid);
    }

    @Override
    public void editTopic(Topic topic) {
        topicMapper.editTopic(topic);
    }

    @Override
    public int addTopic(Topic topic) {
        topicMapper.addTopic(topic);
        return topic.getTopicid();
    }

    @Override
    public void storeTopicImg(TopicImg topicImg) {
        topicImgMapper.storeImg(topicImg);
    }

    @Override
    public List<String> getTopicImgs(int topicid) {
        List<String> topicImgs = topicImgMapper.getTopicImgs(topicid);
        return topicImgs;
    }

    @Override
    public List<Map> getUserTopics(String userid) {
        List<Topic> topics = topicMapper.getUserTopics(userid);
        List<Map> res = new ArrayList<>();
        for (Topic topic : topics) {
            Map<String,Object> map = new HashMap<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            String time = sdf.format(topic.getTime());
            map.put("time",time);

            map.put("topicid",topic.getTopicid());
            map.put("title",topic.getTitle());
            map.put("site",topic.getSite());
            map.put("status",topic.getStatus());

            //判断是图片还是视频
            String image = topicImgMapper.getTopicHeadImg(topic.getTopicid());
            if (null != image){
                int len = image.split("\\.").length - 1;
                String fileType = image.split("\\.")[len];    //截取文件后缀名，判断文件类型
                if (fileType.equalsIgnoreCase("mp4") || fileType.equalsIgnoreCase("wav")){
                    map.put("isimg", 0);
                } else {
                    map.put("isimg", 1);
                }
            }else {
                map.put("isimg", 1);
            }
            map.put("image",image);

            res.add(map);
        }
        return res;
    }

    @Override
    public Map getTopicDetail(int topicid, String usernow_id) {
        Topic topic = topicMapper.getTopicById(topicid);
        Map<String,Object> map = JSONObject.parseObject(JSONObject.toJSONString(topic), Map.class);
        map.remove("site");
        map.remove("type");

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        String time = sdf.format(topic.getTime());
        map.replace("time", time);

        String image = topicImgMapper.getTopicHeadImg(topicid);
        //判断是图片还是视频
        if (null != image){
            int len = image.split("\\.").length - 1;
            String fileType = image.split("\\.")[len];    //截取文件后缀名，判断文件类型
            if (fileType.equalsIgnoreCase("mp4") || fileType.equalsIgnoreCase("wav")){
                map.put("isimg", 0);
            } else {
                map.put("isimg", 1);
            }
        }else {
            map.put("isimg", 1);
        }
        map.put("img", image);

        User user = userMapper.getUserById(topic.getUserid());
        map.put("username", user.getUsername());
        map.put("headimg", user.getHeadimg());

        //是否点赞
        LikeTopic likeTopic = likeTopicMapper.getLikeStatus(usernow_id, topicid);
        if (null == likeTopic){
            map.put("islike", 0);
        }else{
            map.put("islike", 1);
        }

        //是否关注
        Fans fans = fansMapper.getIsFans(new Fans(user.getUserid(), usernow_id));
        if (null == fans){
            map.put("isfocus", 0);
        }else{
            map.put("isfocus", 1);
        }
        return map;
    }

    @Override
    public void deleteTopic(int topicid, String userid) {
        topicMapper.deleteTopic(topicid, userid);
    }
}
