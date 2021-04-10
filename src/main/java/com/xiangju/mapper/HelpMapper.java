package com.xiangju.mapper;

import com.xiangju.domain.Help;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HelpMapper {
    List<Help> getAllHelp();

    Help getHelpById(int helpid);

    void editHelp(Help help);

    void addHelp(Help help);

    List<Help> getUserHelps(String userid);

    void updateHelpStatus(@Param("helpid") int helpid, @Param("status") int status);

    List<Help> getHelpSort(Map map);

    void deleteHelp(int helpid);
}
