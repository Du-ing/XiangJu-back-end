package com.xiangju.controller;

import com.xiangju.domain.LikeTopic;
import com.xiangju.service.LikeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/likeTopic")
public class LikeTopicController {

    @Autowired
    LikeTopicService likeTopicService;

    @PostMapping("/giveLike")
    public void giveLike(@RequestBody LikeTopic likeTopic){
        likeTopicService.giveLike(likeTopic);
    }

    @PostMapping("/removeLike")
    public void removeLike(@RequestBody LikeTopic likeTopic){
        likeTopicService.removeLike(likeTopic);
    }

    @GetMapping("/getUserLikeTopics")
    public List<Map> getUserLikeTopics(@RequestParam String userid){
        return likeTopicService.getUserLikeTopics(userid);
    }
}
