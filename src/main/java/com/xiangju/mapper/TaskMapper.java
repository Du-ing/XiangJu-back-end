package com.xiangju.mapper;

import com.xiangju.domain.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    void addTask(Task task);

    List<Task> getUserTasks(String userid);

    Task getTask(int helpid);

    void submitWork(Task task);

    Task getTaskByTopicid(int topicid);
}
