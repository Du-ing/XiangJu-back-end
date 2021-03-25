package com.xiangju.service.impl;

import com.xiangju.domain.Fans;
import com.xiangju.domain.User;
import com.xiangju.mapper.FansMapper;
import com.xiangju.mapper.UserMapper;
import com.xiangju.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FansSeviceImpl implements FansService {

    @Autowired
    FansMapper fansMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void addFans(Fans fans) {
        fansMapper.addFans(fans);
        userMapper.addFans(fans);
    }

    @Override
    public void removeFans(Fans fans) {
        fansMapper.removeFans(fans);
        userMapper.deleteFans(fans);
    }

    @Override
    public List<Map> getUserAllFans(String userid) {
        List<Fans> fans = fansMapper.getUserAllFans(userid);
        List<Map> res = new ArrayList<>();
        for (Fans fan : fans) {
            Map<String, Object> map = new HashMap<>();
            User user = userMapper.getUserById(fan.getFansid());
            map.put("userid", user.getUserid());
            map.put("username", user.getUsername());
            map.put("headimg", user.getHeadimg());

            Fans isFocus = fansMapper.getIsFans(new Fans(user.getUserid(), userid));
            if (null == isFocus){
                map.put("isfocus", 0);
            }else {
                map.put("isfocus", 1);
            }
            res.add(map);
        }
        return res;
    }

    @Override
    public List<Map> getUserAllFocus(String userid) {
        List<Fans> focus = fansMapper.getUserAllFocus(userid);
        List<Map> res = new ArrayList<>();
        for (Fans focu : focus) {
            Map<String, Object> map = new HashMap<>();
            User user = userMapper.getUserById(focu.getUserid());
            map.put("userid", user.getUserid());
            map.put("username", user.getUsername());
            map.put("headimg", user.getHeadimg());
            res.add(map);
        }
        return res;
    }
}
