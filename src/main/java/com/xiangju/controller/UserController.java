package com.xiangju.controller;

import com.xiangju.domain.User;
import com.xiangju.param.UserUpdateInfo;
import com.xiangju.service.UserService;
import com.xiangju.utils.GetUseridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getUserNum")
    public int getUserNum(){
        return userService.getUserNum();
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(@RequestParam int start,@RequestParam int num){
        List<User> users = userService.getAllUser(start,num);
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(@RequestParam String userid){
        return userService.getUserById(userid);
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody User user){
        String userid = user.getUserid();
        //判断用户是否注册过
        if(null == userService.getUserById(userid)){
            userService.addUser(user);
            return "注册成功！";
        }
        return "此id已被注册！";
    }

    @GetMapping("/deleteUser")
    public void deleteUserById(@RequestParam String userid){
        userService.deleteUserById(userid);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserUpdateInfo userInfo){
        userService.updateUser(userInfo);
    }

    @GetMapping("/searchUser")
    public List<User> searchUsers(@RequestParam String key){
        List<User> users = userService.searchUser(key);
        return users;
    }

    @GetMapping("/getUserid")
    public Object getUserid(@RequestParam String appid,@RequestParam String secret,@RequestParam String code){
        return GetUseridUtil.getUserid(appid,secret,code);
    }
}
