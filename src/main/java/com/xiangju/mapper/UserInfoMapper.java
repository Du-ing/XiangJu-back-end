package com.xiangju.mapper;

import com.xiangju.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {
    void addUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    void getUserInfo(String userid);
}
