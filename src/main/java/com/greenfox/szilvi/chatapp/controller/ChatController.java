package com.greenfox.szilvi.chatapp.controller;

/**
 * Created by Szilvi on 2017. 05. 18..
 */

import javax.servlet.http.HttpServletRequest;
import com.greenfox.szilvi.chatapp.model.*;
import com.greenfox.szilvi.chatapp.model.Error;
import com.greenfox.szilvi.chatapp.repository.MessageRepo;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ChatController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageRepo messageRepo;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Error noNumberException() {
        return new Error("Please provide a number!");
    }

    @RequestMapping
    public String sendMessage(Model model){
        model.addAttribute("messages", messageRepo.findAll());
        return "index";
    }

    @CrossOrigin("*")
    @PostMapping (value = "/api/message/receive")
    public MessageResponse receiveMessage(@RequestBody(required = false)Message message, @RequestBody(required = false) Client client) throws IOException, HttpMessageNotReadableException{
        chatService.saveMessage(message);
        return new MessageResponse(message, client);
    }

    private void receiveLogInfo(HttpServletRequest request) {
        try {
            requestLogger.info(request);
        } catch (Exception e) {
            requestLogger.error(request);
        }
    }

}
