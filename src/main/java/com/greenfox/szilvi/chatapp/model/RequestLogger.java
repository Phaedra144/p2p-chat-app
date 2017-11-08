package com.greenfox.szilvi.chatapp.model;

/**
 * Created by Szilvi on 2017. 05. 18..
 */

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class RequestLogger {

    private static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    private static String getDetails(HttpServletRequest request) {
        return request.getRequestURI() + " " + request.getMethod() + " " + request.getQueryString();
    }

    public static void info(HttpServletRequest request) {
        String logLevel = System.getenv("CHAT_APP_LOGLEVEL");
        if (logLevel != null) {
            System.out.println(getDate() + " INFO: " + getDetails(request));
        }
    }

    public static void error(String message) {
        String logLevel = System.getenv("CHAT_APP_LOGLEVEL");
        if (logLevel != null) {
            if (logLevel.contains("ERROR")) {
                System.err.println(getDate() + " ERROR: " + message);
            }
        }
    }
}
