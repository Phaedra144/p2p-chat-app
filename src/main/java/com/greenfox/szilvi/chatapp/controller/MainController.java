package com.greenfox.szilvi.chatapp.controller;

import com.greenfox.szilvi.chatapp.model.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Controller
public class MainController {

    @Autowired
    RequestLogger requestLogger;

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request) {
        receiveLogInfo(request);
        requestLogger.error(request);
        return "index";
    }

    @RequestMapping(value = "/enter")
    public String register(HttpServletRequest request) {
        receiveLogInfo(request);
        return "register";
    }

    private void receiveLogInfo(HttpServletRequest request) {
        try {
            requestLogger.info(request);
        } catch (Exception e) {
            requestLogger.error(request);
        }
    }
}

