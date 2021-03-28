package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userid;//用户id
    private String username;//用户名
    private String headimg;//头像
    private int gender; //0：女，1：男
    private String address;//地址
    private String job;//职业身份
    private int fans;//粉丝
    private int focus;//关注
    private int integral;//积分
    private String phone;//手机号
    private String sign;//个性签名
}
