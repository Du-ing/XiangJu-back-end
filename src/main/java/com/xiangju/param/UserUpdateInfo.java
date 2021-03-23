package com.xiangju.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改用户信息的接口参数数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateInfo {
    private String userid;
    private String headimg;
    private String username;
    private int gender;
    private String address;
    private String job;
    private String phone;
    private String sign;
}
