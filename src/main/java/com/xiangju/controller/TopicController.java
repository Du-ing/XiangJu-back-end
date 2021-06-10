package com.xiangju.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.domain.Topic;
import com.xiangju.domain.TopicImg;
import com.xiangju.service.TopicService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Value("${file-save-path}")
    private String fileSavePath;

    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping("/getAllTopic")
    public List<Topic> getAllTopic(){
        return topicService.getAllTopic();
    }

    @GetMapping("/getPassTopic")
    public List<Topic> getPassTopic(){
        return topicService.getPassTopic();
    }

    @GetMapping("/getTopic")
    public Topic getTopic(@RequestParam int topicid){
        return topicService.getTopicById(topicid);
    }

    @PostMapping("/editTopic")
    public void editTopic(@RequestBody Topic topic){
        topicService.editTopic(topic);
    }

    @PostMapping("/addTopic")
    public Map<String, Integer> addTopic(@RequestBody Topic topic){
        Integer topicid = topicService.addTopic(topic);
        Map<String, Integer> map = new HashMap<>();
        map.put("topicid",topicid);
        return map;
    }

    /**
     * 上传文件
     * @param uploadfile
     * @param topicid
     * @param index
     * @return
     */
    @SneakyThrows
    @RequestMapping("/uploadFile")
    public Map<String, String> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam int topicid, @RequestParam int index){
        int len = uploadfile.getOriginalFilename().split("\\.").length - 1;
        //获取文件后缀名
        String fileType = uploadfile.getOriginalFilename().split("\\.")[len];
        //拼接成文件名
        String fileName = "TopicImg" + topicid + "_" + index + "." + fileType;

        //文件路径名
        String pathName = fileSavePath + fileName;
        File newFile = new File(pathName);
        //文件写入本地
        uploadfile.transferTo(newFile);

        //文件Url
        String fileUrl = baseUrl + fileName;

        TopicImg topicImg = new TopicImg();
        topicImg.setTopicid(topicid);
        topicImg.setImgUrl(fileUrl);
        //存入数据库
        topicService.storeTopicImg(topicImg);

        Map<String, String> map = new HashMap<>();
        map.put("imgUrl", fileUrl);
        return map;
    }

    @GetMapping("/getTopicImgs")
    public List<String> getTopicImgs(@RequestParam int topicid){
        return topicService.getTopicImgs(topicid);
    }

    @GetMapping("/getUserTopics")
    public List<Map> getUserTopics(@RequestParam String userid){
        return topicService.getUserTopics(userid);
    }

    @PostMapping("/getTopicDetail")
    public Map getTopicDetail(@RequestBody JSONObject jsonObject){
        return topicService.getTopicDetail(Integer.valueOf(jsonObject.get("topicid").toString()), jsonObject.get("userid").toString());
    }

    @GetMapping("/deleteTopic")
    public void deleteTopic(@RequestParam int topicid, @RequestParam String userid){
        topicService.deleteTopic(topicid, userid);
    }

    @GetMapping("/searchTopics")
    public List<Topic> searchTopics(@RequestParam String key){
        return topicService.searchTopicsByManage(key);
    }
}
