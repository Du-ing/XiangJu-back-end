package com.xiangju.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.Help;
import com.xiangju.domain.Task;
import com.xiangju.domain.User;
import com.xiangju.mapper.HelpImgMapper;
import com.xiangju.mapper.HelpMapper;
import com.xiangju.mapper.TaskMapper;
import com.xiangju.mapper.UserMapper;
import com.xiangju.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    HelpMapper helpMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    HelpImgMapper helpImgMapper;

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
        //添加任务时要修改相应求助的状态
        helpMapper.updateHelpStatus(task.getHelpid(),task.getStatus());
    }

    @Override
    public List<Map> getUserTasks(String userid) {
        List<Task> tasks = taskMapper.getUserTasks(userid);
        List<Map> res = new ArrayList<>();
        for (Task task : tasks) {
            Map<String,Object> map = new HashMap<>();
            Help help = helpMapper.getHelpById(task.getHelpid());
            //加入任务对应的求助的相关信息
            map.put("helpid",task.getHelpid());
            map.put("status",task.getStatus());

            //格式化时间戳
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endtime = sdf.format(help.getEndtime());
            map.put("endtime",endtime);

            map.put("title",help.getTitle());
            map.put("reward",help.getReward());
            map.put("site",help.getSite());
            //加入发布任务的用户信息
            User user_pub = userMapper.getUserById(help.getUserid());
            map.put("userid",user_pub.getUserid());
            map.put("username",user_pub.getUsername());
            map.put("headimg",user_pub.getHeadimg());

            res.add(map);
        }
        return res;
    }

    @Override
    public Map getTaskDetail(int helpid) {
        Task task = taskMapper.getTask(helpid);
        Help help = helpMapper.getHelpById(task.getHelpid());
        Map<String,Object> map = JSONObject.parseObject(JSONObject.toJSONString(help),Map.class);
//        map.remove("userid");//去掉当前用户id

        //格式化时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startime = sdf.format(help.getStartime());
        String endtime = sdf.format(help.getEndtime());
        map.replace("startime",startime);
        map.replace("endtime",endtime);

        //所有图片素材
        List<String> imgs = helpImgMapper.getHelpImgs(helpid);
        map.put("imgs", imgs);

        User user = userMapper.getUserById(help.getUserid());
        //加入求助发布者的信息
        map.put("username",user.getUsername());
        map.replace("userid",user.getUserid());
        map.put("headimg",user.getHeadimg());
        return map;
    }

    @Override
    public void submitWork(Task task) {
        taskMapper.submitWork(task);
        //同时需要修改求助的状态
        helpMapper.updateHelpStatus(task.getHelpid(),task.getStatus());
    }
}
