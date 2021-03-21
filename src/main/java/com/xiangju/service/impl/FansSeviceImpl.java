package com.xiangju.service.impl;

import com.xiangju.domain.Fans;
import com.xiangju.mapper.FansMapper;
import com.xiangju.mapper.UserMapper;
import com.xiangju.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
