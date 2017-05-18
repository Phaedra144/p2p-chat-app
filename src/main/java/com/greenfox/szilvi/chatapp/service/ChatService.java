package com.greenfox.szilvi.chatapp.service;

import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Component
public class ChatService {

    @Autowired
    UserRepo repo;

    public User saveUserWithNewName(String username, User user) {
        user.setUsername(username);
        repo.save(user);
        return user;
    }
}
