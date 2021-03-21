package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userid;
    private String username;
    private String headimg;//头像
    private int gender; //0：女，1：男
    private String address;//地址
    private String job;
    private int fans;//粉丝
    private int focus;//关注
    private int integral;//积分
}
