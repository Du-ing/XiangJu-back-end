package com.xiangju.controller;

import com.xiangju.domain.CommentTopic;
import com.xiangju.service.CommentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commentTopic")
public class CommentTopicController {

    @Autowired
    CommentTopicService commentTopicService;

    @PostMapping("/sendCommentTopic")
    public void sendCommentTopic(@RequestBody CommentTopic comment){
        commentTopicService.addTopicComment(comment);
    }

    @GetMapping("/getTopicComments")
    public List<Map> getTopicComments(@RequestParam int topicid){
        return commentTopicService.getTopicComments(topicid);
    }
}
