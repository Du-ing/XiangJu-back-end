package com.xiangju.controller;

import com.xiangju.domain.Fans;
import com.xiangju.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fans")
public class FansController {

    @Autowired
    FansService fansService;

    @PostMapping("/addFans")
    public void addFans(@RequestBody Fans fans){
        fansService.addFans(fans);
    }

    @PostMapping("/removeFans")
    public void removeFans(@RequestBody Fans fans){
        fansService.removeFans(fans);
    }

    @GetMapping("/getUserAllFans")
    public List<Map> getUserAllFans(@RequestParam String userid){
        return fansService.getUserAllFans(userid);
    }

    @GetMapping("/getUserAllFocus")
    public List<Map> getUserAllFocus(@RequestParam String userid){
        return fansService.getUserAllFocus(userid);
    }
}
