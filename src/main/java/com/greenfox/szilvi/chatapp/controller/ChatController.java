package com.greenfox.szilvi.chatapp.controller;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greenfox.szilvi.chatapp.model.RequestLogger;
import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    UserRepo repo;

    @GetMapping(value = "/login")
    public void home(HttpServletRequest request) {
        try {
            requestLogger.info(request);
        } catch (Exception e) {
            requestLogger.error(request);
        }
    }

    @PostMapping(value = "/register")
    public void saveUser(HttpServletResponse response, HttpServletRequest request, String username)
            throws IOException {
        try {
            requestLogger.info(request);
        } catch (Exception e) {
            requestLogger.error(request);
        }
        User user = new User();
        user.setUsername(username);
        repo.save(user);
        response.sendRedirect("/?username=" + user.getUsername());
    }
}
