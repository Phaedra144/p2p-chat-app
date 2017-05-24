package com.greenfox.szilvi.chatapp.service;

import com.greenfox.szilvi.chatapp.model.Message;
import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.repository.MessageRepo;
import com.greenfox.szilvi.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Component
public class ChatService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MessageRepo messageRepo;

    public List<User> getUsers() {
        return (List<User>) userRepo.findAll();
    }

    public User saveUserWithNewName(String username, User user) {
        user.setUsername(username);
        userRepo.save(user);
        return user;
    }

    public User saveUser(User user) {
        userRepo.save(user);
        return user;
    }

    public void saveMessage(Message message){
        messageRepo.save(message);
    }
}
