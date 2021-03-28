package com.xiangju.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.Help;
import com.xiangju.domain.LikeTopic;
import com.xiangju.domain.Topic;
import com.xiangju.mapper.*;
import com.xiangju.param.HelpType;
import com.xiangju.service.HomeService;
import com.xiangju.utils.GetTimeLimitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    TopicMapper topicMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TopicImgMapper topicImgMapper;
    @Autowired
    HelpMapper helpMapper;
    @Autowired
    HelpImgMapper helpImgMapper;
    @Autowired
    LikeTopicMapper likeTopicMapper;

    /**
     * 扩展方法
     * 将数据库中查到的Topic处理为前端需要的数据
     * @param topics
     * @return
     */
    public List<Map> topicToRes(List<Topic> topics, String userid_now){
        List<Map> res = new ArrayList<>();
        for (Topic topic : topics) {
            int topicid = topic.getTopicid();
            String userid = topic.getUserid();
//            List<String> topicImgList = topicImgMapper.getTopicImgs(topicid);
//            String image = "";
//            if(!topicImgList.isEmpty()){
//                image = topicImgList.get(0);
//            }

            Map<String,Object> map = JSONObject.parseObject(JSONObject.toJSONString(topic),Map.class);
            map.put("username",userMapper.getUserById(userid).getUsername());
            map.put("headimg",userMapper.getUserById(userid).getHeadimg());

            //TODO:判断是图片还是视频
            String headimg = topicImgMapper.getTopicHeadImg(topicid);
            int len = headimg.split("\\.").length - 1;
            String fileType = headimg.split("\\.")[len];
            if (fileType.equalsIgnoreCase("mp4")){

            }

            map.put("image",headimg);

            //是否点过赞
            LikeTopic likeTopic = likeTopicMapper.getLikeStatus(userid_now, topicid);
            if (null != likeTopic){
                map.put("islike",1);
            }else {
                map.put("islike",0);
            }

            //格式化时间戳
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time = sdf.format(topic.getTime());
            map.replace("time",time);
            res.add(map);
        }
        return res;
    }

    @Override
    public List<Map> home(String userid_now, String type) {
        List<Topic> topics = null;
        if (type.equals("推荐")){
            topics = topicMapper.getAllTopic();
        }else {
            topics = topicMapper.getTopicByType(type);
        }
        return topicToRes(topics, userid_now);
    }

    @Override
    public List<Map> helpHome() {
        List<Help> helps = helpMapper.getAllHelp();
        List<Map> res = new ArrayList<>();
        for (Help help : helps) {
            int helpid = help.getHelpid();
            String userid = help.getUserid();
//            List<String> helpImgList = helpImgMapper.getHelpImgs(helpid);
//            String image = "";
//            if (!helpImgList.isEmpty()){
//                image = helpImgList.get(0);
//            }
            String image = helpImgMapper.getHelpHeadImg(helpid);

            Map<String,Object> map = JSONObject.parseObject(JSONObject.toJSONString(help),Map.class);
            map.put("username", userMapper.getUserById(userid).getUsername());
            map.put("headimg",userMapper.getUserById(userid).getHeadimg());
            map.put("image", image);

            //格式化时间戳
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String startime = sdf.format(help.getStartime());
            map.replace("startime",startime);
            String endtime = sdf.format(help.getEndtime());
            map.replace("endtime",endtime);
            res.add(map);
        }
        return res;
    }

    @Override
    public List<Map> helpSortHome(HelpType helpType) {
        Map<String,Object> params = new HashMap<>();

        Date timeLimit = GetTimeLimitUtil.getTimeLimit(helpType.getTimeLimit());
        params.put("startime",timeLimit);
        params.put("site",helpType.getSite());
        params.put("type",helpType.getType());
        params.put("min",helpType.getMin());
        params.put("max",helpType.getMax());

        List<Map> res = new ArrayList<>();
        List<Help> helps = helpMapper.getHelpSort(params);
        for (Help help : helps) {
            int helpid = help.getHelpid();
            String userid = help.getUserid();
            String image = helpImgMapper.getHelpHeadImg(helpid);

            Map<String,Object> map = JSONObject.parseObject(JSONObject.toJSONString(help),Map.class);
            map.put("username", userMapper.getUserById(userid).getUsername());
            map.put("headimg",userMapper.getUserById(userid).getHeadimg());
            map.put("image", image);

            //格式化时间戳
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String startime = sdf.format(help.getStartime());
            map.replace("startime",startime);
            String endtime = sdf.format(help.getEndtime());
            map.replace("endtime",endtime);
            res.add(map);
        }
        return res;
    }

    @Override
    public List<Map> searchTopics(String key, String userid) {
        List<Topic> topics = topicMapper.searchTopics(key);
        return topicToRes(topics, userid);
    }
}
