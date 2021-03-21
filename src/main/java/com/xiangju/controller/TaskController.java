package com.xiangju.controller;

import com.xiangju.domain.Task;
import com.xiangju.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/addTask")
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @GetMapping("/getUserTasks")
    public List<Map> getUserTasks(@RequestParam String userid){
        return taskService.getUserTasks(userid);
    }

    @GetMapping("/getTaskDetail")
    public Map getTaskDetail(@RequestParam int helpid){
        return taskService.getTaskDetail(helpid);
    }

    @PostMapping("/submitWork")
    public String submitWork(@RequestBody Task task){
        taskService.submitWork(task);
        return "提交作品成功！";
    }
}
