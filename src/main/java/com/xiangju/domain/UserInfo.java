package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userid;
    private String username;
    private int gender;
    private int age;
    private Date birth;
    private String company;
    private String school;
    private String phone;
    private String email;
    private String signature;
}
