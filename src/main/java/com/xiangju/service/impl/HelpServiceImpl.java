package com.xiangju.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.Help;
import com.xiangju.domain.HelpImg;
import com.xiangju.domain.Task;
import com.xiangju.domain.User;
import com.xiangju.mapper.HelpImgMapper;
import com.xiangju.mapper.HelpMapper;
import com.xiangju.mapper.TaskMapper;
import com.xiangju.mapper.UserMapper;
import com.xiangju.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    HelpMapper helpMapper;
    @Autowired
    HelpImgMapper helpImgMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Help> getAllHelp() {
        List<Help> helps = helpMapper.getAllHelp();
        return helps;
    }

    @Override
    public Help getHelpById(int helpid) {
        return helpMapper.getHelpById(helpid);
    }

    @Override
    public void editHelp(Help help) {
        helpMapper.editHelp(help);
    }

    @Override
    public int addHelp(Help help) {
        helpMapper.addHelp(help);
        return help.getHelpid();
    }

    @Override
    public void storeHelpImg(HelpImg helpImg) {
        helpImgMapper.storeImg(helpImg);
    }

    @Override
    public List<String> getHelpImgs(int helpid) {
        return helpImgMapper.getHelpImgs(helpid);
    }

    @Override
    public List<Map> getUserHelps(String userid) {
        List<Help> helps = helpMapper.getUserHelps(userid);
        List<Map> res = new ArrayList<>();
        for (Help help : helps) {
            Map<String,Object> map = new HashMap<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            String startime = sdf.format(help.getStartime());
            map.put("startime",startime);

            map.put("helpid",help.getHelpid());
            map.put("title",help.getTitle());
            map.put("reward",help.getReward());
            map.put("site",help.getSite());
            map.put("status",help.getStatus());

            Task task = taskMapper.getTask(help.getHelpid());
            if (null != task){
                //求助被接单，加入接单者的信息
                User user = userMapper.getUserById(task.getUserid());
                map.put("userid",user.getUserid());
                map.put("username",user.getUsername());
                map.put("headimg",user.getHeadimg());
            }
            res.add(map);
        }
        return res;
    }

    @Override
    public Map getHelpDetail(int helpid) {
        Help help = helpMapper.getHelpById(helpid);
        Map map = JSONObject.parseObject(JSONObject.toJSONString(help),Map.class);
        map.remove("userid");

        //格式化时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startime = sdf.format(help.getStartime());
        String endtime = sdf.format(help.getEndtime());
        map.replace("startime",startime);
        map.replace("endtime",endtime);

        //所有图片素材
        List<String> imgs = helpImgMapper.getHelpImgs(helpid);
        map.put("imgs", imgs);

        Task task = taskMapper.getTask(helpid);
        if(null != task) {
            User user = userMapper.getUserById(task.getUserid());
            map.put("userid", user.getUserid());
            map.put("username", user.getUsername());
            map.put("headimg", user.getHeadimg());
        }
        return map;
    }

    @Override
    public void deleteHelp(int helpid, String userid) {
        helpMapper.deleteHelp(helpid, userid);
    }
}
