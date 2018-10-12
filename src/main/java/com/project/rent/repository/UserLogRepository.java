package com.project.rent.repository;

import com.project.rent.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    UserLog findUserLogById(int id);
    UserLog findUserLogByIp(String ip);
    UserLog findUserLogByUserId(int id);

}
