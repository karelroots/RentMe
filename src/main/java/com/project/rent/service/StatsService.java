package com.project.rent.service;

import com.project.rent.model.UserLog;
import com.project.rent.repository.UserLogRepository;
import com.project.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("statsService")
public class StatsService {
    private UserLogRepository userLogRepository;
    private UserRepository userRepository;

    @Autowired
    public StatsService(UserLogRepository userLogRepository, UserRepository userRepository) {
        this.userLogRepository = userLogRepository;
        this.userRepository = userRepository;
    }

    public UserLog findUserLogByUserId(int id) {
        return userLogRepository.findUserLogByUserId(id);
    }

    public void saveLog(UserLog userLog) {
        userLogRepository.save(userLog);
    }

    public List<UserLog> getUserLogList() {

        List<UserLog> logs = userLogRepository.findAll();

        Collections.sort(logs); // Sorteerime ajaliselt uuemad logid enne

        for(UserLog log:logs) {
            // lisame logisse kasutaja id-le vastava kasutajanime
            log.setUsername(userRepository.findById(log.getUserId()).getUsername());
        }

        return logs;
    }

    public List<UserLog> getLastTenList() {

        List<UserLog> logs = this.getUserLogList();
        List<UserLog> lastTen = new ArrayList<>();

        if(logs.size() >= 10) {
            for(int i = 0; i<10; i++) { // leiame viimased 10 logi
                lastTen.add(logs.get(i));
            }
        } else {
            return logs; // kui meil on vähem kui 10 logikirjet, siis tagastame algse listi
        }

        return lastTen; // tagastame ainult viimased 10
    }
}
