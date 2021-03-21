package com.xiangju.controller;

import com.xiangju.domain.LikeTopic;
import com.xiangju.service.LikeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
