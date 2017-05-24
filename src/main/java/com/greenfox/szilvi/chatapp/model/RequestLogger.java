package com.greenfox.szilvi.chatapp.model;

/**
 * Created by Szilvi on 2017. 05. 18..
 */

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

@Component
public class RequestLogger {

    Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public void info(HttpServletRequest request) {
        root.info(
                new LogEntry(new UrlPathHelper().getPathWithinApplication(request), request.getMethod(), request.getParameterMap()).toString());
    }

    public void error(HttpServletRequest request) {
        root.error(new LogEntry(new UrlPathHelper().getPathWithinApplication(request), request.getMethod(), request.getParameterMap()).toString());
    }

    public Logger getRoot() {
        return root;
    }

    public void receiveLogInfo(HttpServletRequest request) {
        try {
            info(request);
        } catch (Exception e) {
            error(request);
        }
    }
}
