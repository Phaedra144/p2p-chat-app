package com.greenfox.szilvi.chatapp.controller;

/**
 * Created by Szilvi on 2017. 05. 18..
 */

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greenfox.szilvi.chatapp.model.RequestLogger;
import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    ChatService chatService;

    @PostMapping(value = "/register")
    public void saveUser(HttpServletResponse response, HttpServletRequest request, String username)
            throws IOException {
        receiveLogInfo(request);
        User user = new User();
        chatService.saveUserWithNewName(username, user);
        response.sendRedirect("/?username=" + user.getUsername());
    }

    private void receiveLogInfo(HttpServletRequest request) {
        try {
            requestLogger.info(request);
        } catch (Exception e) {
            requestLogger.error(request);
        }
    }
}
