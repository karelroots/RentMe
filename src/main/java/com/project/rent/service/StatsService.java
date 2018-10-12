package com.project.rent.service;

import com.project.rent.model.UserLog;
import com.project.rent.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statsService")
public class StatsService {
    private UserLogRepository userLogRepository;

    @Autowired
    public StatsService(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public UserLog findUserLogByUserId(int id) {
        return userLogRepository.findUserLogByUserId(id);
    }

    public void saveLog(UserLog userLog) {
        userLogRepository.save(userLog);
    }

    public List<UserLog> getUserLogList() {
        return userLogRepository.findAll();
    }
}
