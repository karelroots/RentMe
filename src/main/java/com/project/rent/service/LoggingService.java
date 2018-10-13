package com.project.rent.service;

import com.project.rent.model.UserLog;
import lombok.extern.slf4j.Slf4j;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class LoggingService extends InMemoryHttpTraceRepository {

    @Autowired
    UserService userService;

    @Autowired
    StatsService statsService;

    @Override
    public void add(HttpTrace trace) {
        //super.add(trace);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) { // tegemist on autoriseeritud kasutajaga

            HttpTrace.Request rq = trace.getRequest();

            String page = rq.getUri().getPath();
            String user = auth.getName();

            if (!page.contains(".")) { // laetud leht on html

                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser(); // User Agent'i parsimiseks
                UserLog userLog = new UserLog();

                ReadableUserAgent agent = parser.parse(rq.getHeaders().get("user-agent").get(0));
                String browserName = agent.getName();
                String browserVersion = agent.getVersionNumber().toVersionString();
                String osName = agent.getOperatingSystem().getName();
                String osVersion = agent.getOperatingSystem().getVersionNumber().toVersionString();

                userLog.setUserId(userService.findUserByEmail(user).getId()); // leiame sellise e-mailiga kasutaja id ja loggime
                userLog.setOs(osName + " " + osVersion); // loggime kasutaja operatsiooni süsteemi
                userLog.setLandingPage(rq.getUri().getPath()); // loggime landing page'i
                userLog.setBrowser(browserName + "/" + browserVersion); // loggime kasutaja brauseri
                userLog.setDatetime(LocalDateTime.now().toString()); // logime aja

                statsService.saveLog(userLog);

            }
        }
    }
}