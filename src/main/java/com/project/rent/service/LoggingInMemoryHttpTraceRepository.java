package com.project.rent.service;

import com.project.rent.model.UserLog;
import com.project.rent.repository.UserLogRepository;
import com.project.rent.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.VersionNumber;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LoggingInMemoryHttpTraceRepository extends InMemoryHttpTraceRepository {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLogRepository userLogRepository;


    @Override
    public void add(HttpTrace trace) {
        super.add(trace);
        //log.info("Trace:" + ToStringBuilder.reflectionToString(trace));
        //log.info("Request:" + ToStringBuilder.reflectionToString(trace.getRequest()));
        //log.info("Response:" + ToStringBuilder.reflectionToString(trace.getResponse()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        HttpTrace.Request rq = trace.getRequest();

        String page = rq.getUri().getPath();
        String user = auth.getName();

        if (user != null && !page.contains(".")) {
            UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
            UserLog userLog = new UserLog();
            System.out.println(trace.getRequest().getHeaders().get("user-agent").get(0));
            System.out.println(trace.getRequest().getRemoteAddress());
            System.out.println(trace.getRequest().getUri().getPath());
            System.out.println(user);

            ReadableUserAgent agent = parser.parse(rq.getHeaders().get("user-agent").get(0));
            String name = agent.getName();
            VersionNumber version = agent.getVersionNumber();

            System.out.println(name + "/" + version.toVersionString());

            userLog.setUserId(userRepository.findByEmail(user).getId()); // leiame sellise e-mailiga kasutaja id ja loggime
            userLog.setIp(rq.getRemoteAddress()); // loggime kasutaja ip
            userLog.setLandingPage(rq.getUri().getPath()); // loggime landing page'i
            userLog.setBrowser(name + "/" + version.toVersionString()); // loggime kasutaja brauseri

            userLogRepository.save(userLog);

        }
    }
}