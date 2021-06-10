package com.xiangju.controller;

import com.xiangju.domain.Help;
import com.xiangju.domain.HelpImg;
import com.xiangju.service.HelpService;
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
@RequestMapping("/help")
public class HelpController {

    @Value("${file-save-path}")
    private String fileSavePath;

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    HelpService helpService;

    @GetMapping("/getAllHelp")
    public List<Help> getAllHelp(){
        return helpService.getAllHelp();
    }

    @GetMapping("/getPassHelp")
    public List<Help> getPassHelp(){
        return helpService.getPassHelp();
    }

    @GetMapping("/getHelp")
    public Help getHelp(@RequestParam int helpid){
        return helpService.getHelpById(helpid);
    }

    @PostMapping("/editHelp")
    public void editHelp(@RequestBody Help help){
        helpService.editHelp(help);
    }

    @PostMapping("/addHelp")
    public Map<String, Integer> addHelp(@RequestBody Help help){
//        System.out.println(help.toString());
        Integer helpid = helpService.addHelp(help);
        Map<String, Integer> map = new HashMap<>();
        map.put("helpid",helpid);
        return map;
    }

    @SneakyThrows
    @RequestMapping("/uploadFile")
    public Map<String, String> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam int helpid, @RequestParam int index){
        int len = uploadfile.getOriginalFilename().split("\\.").length - 1;
        //获取文件后缀名
        String fileType = uploadfile.getOriginalFilename().split("\\.")[len];
        //拼接成文件名
        String fileName = "HelpImg" + helpid + "_" + index + "." + fileType;

        //文件路径名
        String pathName = fileSavePath + fileName;
        File newFile = new File(pathName);
        //文件写入本地
        uploadfile.transferTo(newFile);

        //文件Url
        String fileUrl = baseUrl + fileName;

        HelpImg helpImg = new HelpImg();
        helpImg.setHelpid(helpid);
        helpImg.setImgUrl(fileUrl);
        //存入数据库
        helpService.storeHelpImg(helpImg);

        Map<String, String> map = new HashMap<>();
        map.put("imgUrl", fileUrl);

        return map;
    }

    @GetMapping("/getHelpImgs")
    public List<String> getHelpImgs(@RequestParam int helpid){
        return helpService.getHelpImgs(helpid);
    }

    @GetMapping("/getUserHelps")
    public List<Map> getUserHelps(@RequestParam String userid){
        return helpService.getUserHelps(userid);
    }

    @GetMapping("/getHelpDetail")
    public Map getHelpDetail(@RequestParam int helpid){
        return helpService.getHelpDetail(helpid);
    }

    @GetMapping("/deleteHelp")
    public void deleteHelp(@RequestParam int helpid, @RequestParam String userid){
        helpService.deleteHelp(helpid, userid);
    }

    @GetMapping("/searchHelps")
    public List<Help> searchHelps(@RequestParam String key){
        return helpService.searchHelps(key);
    }
}
