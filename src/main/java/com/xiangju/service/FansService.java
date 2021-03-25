package com.xiangju.service;

import com.xiangju.domain.Fans;

import java.util.List;
import java.util.Map;

public interface FansService {
    void addFans(Fans fans);

    void removeFans(Fans fans);

    List<Map> getUserAllFans(String userid);

    List<Map> getUserAllFocus(String userid);
}
