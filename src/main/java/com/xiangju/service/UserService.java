package com.xiangju.service;

import com.xiangju.domain.User;

import java.util.List;

public interface UserService {
    //获取用户数量
    int getUserNum();
    //分页获取所有用户
    List<User> getAllUser(int start,int num);
    //获取指定用户
    User getUserById(String userid);
    //注册用户
    void addUser(User user);
    //删除指定用户
    void deleteUserById(String userid);
    //更新用户
    void updateUser(User user);
    //模糊查询用户昵称
    List<User> searchUser(String key);
    //获取用户详细信息
//    Map getUserDetail(String userid);
}
