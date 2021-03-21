package com.xiangju.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiangju.param.HelpType;
import com.xiangju.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/home")
    public List<Map> home(@RequestBody JSONObject jsonObject){
        return homeService.home(jsonObject.get("userid").toString(), jsonObject.get("type").toString());
    }

    @GetMapping("/helpHome")
    public List<Map> helpHome(){
        return homeService.helpHome();
    }

    @PostMapping("/helpSortHome")
    public List<Map> helpSortHome(@RequestBody HelpType helpType){
        return homeService.helpSortHome(helpType);
    }

    @PostMapping("/searchTopics")
    public List<Map> searchTopics(@RequestBody JSONObject jsonObject){
        return homeService.searchTopics(jsonObject.get("key").toString(), jsonObject.get("userid").toString());
    }
}
