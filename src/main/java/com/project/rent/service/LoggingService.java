package com.project.rent.service;

import com.project.rent.model.UserLog;
import com.project.rent.repository.UserLogRepository;
import com.project.rent.repository.UserRepository;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.VersionNumber;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggingService extends DispatcherServlet {

    //private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    UserLogRepository userLogRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache) {

        String page = requestToCache.getRequestURI();
        String user = requestToCache.getRemoteUser();

        if(user != null && !page.contains(".")) {
            UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
            UserLog userLog = new UserLog();
            System.out.println(requestToCache.getRequestURI());
            System.out.println(requestToCache.getRemoteHost());
            System.out.println(requestToCache.getRemoteUser());
            ReadableUserAgent agent = parser.parse(requestToCache.getHeader("User-Agent"));
            String name = agent.getName();
            VersionNumber version = agent.getVersionNumber();

            System.out.println(name+"/"+version.toVersionString());

            userLog.setUserId(userRepository.findByEmail(
                    requestToCache.getRemoteUser()).getId()); // leiame sellise e-mailiga kasutaja id ja loggime
            userLog.setIp(requestToCache.getRemoteHost()); // loggime kasutaja ip
            userLog.setLandingPage(requestToCache.getRequestURI()); // loggime landing page'i
            userLog.setBrowser(name+"/"+version.toVersionString()); // loggime kasutaja brauseri

        }

    }
    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

}
