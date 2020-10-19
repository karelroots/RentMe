package com.project.rent.service;

import com.project.rent.model.Browser;
import com.project.rent.model.Landing;
import com.project.rent.model.OpSys;
import com.project.rent.model.UserLog;
import com.project.rent.repository.OfferRepository;
import com.project.rent.repository.UserLogRepository;
import com.project.rent.repository.UserRepository;
import com.project.rent.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("statsService")
public class StatsService {
    private final UserLogRepository userLogRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final WishRepository wishRepository;

    @Autowired
    public StatsService(@Autowired UserLogRepository userLogRepository,
                        @Autowired UserRepository userRepository,
                        @Autowired OfferRepository offerRepository,
                        @Autowired WishRepository wishRepository) {
        this.userLogRepository = userLogRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.wishRepository = wishRepository;
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

    public List<Browser> getTopBrowsers() {
        List<String> browsers = userLogRepository.findBrowsers();
        List<Browser> topBrowsers = new ArrayList<>();

        for(String browser:browsers) {
            topBrowsers.add(new Browser(browser, userLogRepository.findBrowserCount(browser)));
        }
        Collections.sort(topBrowsers);

        return topBrowsers;
    }

    public List<OpSys> getTopOs() {
        List<String> opsystems = userLogRepository.findOpSystems();
        List<OpSys> topOs = new ArrayList<>();

        for(String os:opsystems) {
            topOs.add(new OpSys(os, userLogRepository.findOpSystemCount(os)));
        }
        Collections.sort(topOs);

        return topOs;
    }

    public List<Landing> getTopLanding() {
        List<String> landingPages = userLogRepository.findLandingPages();
        List<Landing> topLanding = new ArrayList<>();

        for(String landing:landingPages) {
            topLanding.add(new Landing(landing, userLogRepository.findLandingCount(landing)));
        }
        Collections.sort(topLanding);

        return topLanding;
    }

    public int getUserOffers(int userId) {
        int count = offerRepository.findOfferCount(userId);
        return count;
    }

    public int getUserWishes(int userId) {
        int count = wishRepository.findWishCount(userId);
        return  count;
    }
}
