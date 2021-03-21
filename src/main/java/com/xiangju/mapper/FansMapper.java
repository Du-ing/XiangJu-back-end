package com.xiangju.mapper;

import com.xiangju.domain.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FansMapper {
    void addFans(Fans fans);

    void removeFans(Fans fans);

    List<Fans> getUserAllFans(String userid);

    List<Fans> getUserAllFocus(String userid);

    Fans getIsFans(Fans fans);
}
