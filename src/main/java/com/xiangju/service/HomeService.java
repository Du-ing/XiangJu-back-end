package com.xiangju.service;

import com.xiangju.param.HelpType;

import java.util.List;
import java.util.Map;

public interface HomeService {
    List<Map> home(String userid, String type);

    List<Map> helpHome();

    List<Map> helpSortHome(HelpType helpType);

    //模糊搜索
    List<Map> searchTopics(String key, String userid);
}
