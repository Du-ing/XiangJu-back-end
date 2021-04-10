package com.xiangju.service;

import com.xiangju.domain.Help;
import com.xiangju.domain.HelpImg;

import java.util.List;
import java.util.Map;

public interface HelpService {
    List<Help> getAllHelp();

    Help getHelpById(int helpid);

    void editHelp(Help help);

    int addHelp(Help help);

    void storeHelpImg(HelpImg helpImg);

    List<String> getHelpImgs(int helpid);

    List<Map> getUserHelps(String userid);

    Map getHelpDetail(int helpid);

    void deleteHelp(int helpid, String userid);
}
