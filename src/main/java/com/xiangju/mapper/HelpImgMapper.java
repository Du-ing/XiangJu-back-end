package com.xiangju.mapper;

import com.xiangju.domain.HelpImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HelpImgMapper {
    void storeImg(HelpImg helpImg);

    List<String> getHelpImgs(int helpid);

    String getHelpHeadImg(int helpid);
}
