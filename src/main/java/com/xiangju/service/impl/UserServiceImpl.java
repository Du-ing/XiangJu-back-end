package com.xiangju.service.impl;

import com.xiangju.domain.User;
import com.xiangju.mapper.UserMapper;
import com.xiangju.param.UserUpdateInfo;
import com.xiangju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int getUserNum() {
        return userMapper.getUserNum();
    }

    @Override
    public List<User> getAllUser(int start,int num) {
        List<User> users = userMapper.getAllUser(start,num);
        return users;
    }

    @Override
    public User getUserById(String userid) {
        return userMapper.getUserById(userid);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUserById(String userid) {
        userMapper.deleteUserById(userid);
    }

    @Override
    public void updateUser(UserUpdateInfo userInfo) {
        userMapper.updateUser(userInfo);
    }

    @Override
    public List<User> searchUser(String key) {
        key = "%" + key + "%";
        List<User> users = userMapper.searchUser(key);
        return users;
    }

//    @Override
//    public Map getUserDetail(String userid) {
//        User user = userMapper.getUserById(userid);
//        JSONObject.parseObject(JSONObject.toJSONString(user), Map.class);
//
//        return null;
//    }
}
