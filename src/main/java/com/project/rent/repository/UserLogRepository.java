package com.project.rent.repository;

import com.project.rent.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    UserLog findUserLogById(int id);
    UserLog findUserLogByUserId(int id);

    @Query(
            value = "SELECT count(browser) from user_browser where browser = ?1", // userBrowser on andmebaasis vaade
            nativeQuery = true)
    int findBrowserCount(String browser);

    @Query(
            value ="SELECT DISTINCT browser FROM user_browser",
                    nativeQuery = true)
    List<String> findBrowsers();

    @Query(
            value = "SELECT count(os) from user_os where os = ?1", // userOs on andmebaasis vaade
            nativeQuery = true)
    int findOpSystemCount(String os);

    @Query(
            value ="SELECT DISTINCT os FROM user_os",
            nativeQuery = true)
    List<String> findOpSystems();

    @Query(
            value = "SELECT count(landing_page) from landing_pages where landing_page = ?1", // landingpages on andmebaasis vaade
            nativeQuery = true)
    int findLandingCount(String landingPage);

    @Query(
            value ="SELECT DISTINCT landing_page FROM landing_pages",
            nativeQuery = true)
    List<String> findLandingPages();

}
