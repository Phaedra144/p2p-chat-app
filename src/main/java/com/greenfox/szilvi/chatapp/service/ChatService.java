package com.greenfox.szilvi.chatapp.service;

import com.greenfox.szilvi.chatapp.model.IncomingMessage;
import com.greenfox.szilvi.chatapp.model.Message;
import com.greenfox.szilvi.chatapp.model.StatusResponse;
import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.repository.MessageRepo;
import com.greenfox.szilvi.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public static StatusResponse post(IncomingMessage incomingMessage) {
        RestTemplate template = new RestTemplate();
        String url = System.getenv("CHAT_APP_PEER_ADDRESS") + "api/message/receive";
        StatusResponse status = new StatusResponse();
        try {
            status = template.postForObject(url, incomingMessage, StatusResponse.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void saveMessage(Message message){
        messageRepo.save(message);
    }

    public List<Message> findAllMessages() {
        return messageRepo.findAllByOrderByTimestampAsc();
    }

    public User getUser() {
        return userRepo.findOne(1l);
    }
}
