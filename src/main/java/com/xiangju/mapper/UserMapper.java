package com.xiangju.mapper;

import com.xiangju.domain.Fans;
import com.xiangju.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int getUserNum();

    List<User> getAllUser(int start,int num);

    User getUserById(String userid);

    void addUser(User user);

    void deleteUserById(String userid);

    void updateUser(User user);

    List<User> searchUser(String key);

    void addFans(Fans fans);

    void deleteFans(Fans fans);
}
