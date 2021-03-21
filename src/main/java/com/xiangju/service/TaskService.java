package com.xiangju.service;

import com.xiangju.domain.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    void addTask(Task task);

    List<Map> getUserTasks(String userid);

    Map getTaskDetail(int helpid);

    void submitWork(Task task);
}
