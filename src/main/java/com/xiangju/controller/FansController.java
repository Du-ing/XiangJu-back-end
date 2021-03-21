package com.xiangju.controller;

import com.xiangju.domain.Fans;
import com.xiangju.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
