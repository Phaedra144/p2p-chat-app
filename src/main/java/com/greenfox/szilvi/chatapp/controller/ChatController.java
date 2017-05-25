package com.greenfox.szilvi.chatapp.controller;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.greenfox.szilvi.chatapp.model.*;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ChatController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    ChatService chatService;

    @ExceptionHandler
    public void error(Exception ex) {
        requestLogger.error(ex.getMessage());
    }

    @ModelAttribute
    public void log(HttpServletRequest request) {
        requestLogger.info(request);
    }

    @CrossOrigin("*")
    @PostMapping("/api/message/receive")
    public ResponseEntity<?> receiveMessage(@RequestBody IncomingMessage received) throws JsonParseException {
        ArrayList<String> missing = new ArrayList<>();
        if (received.getMessage() == null) {
            missing.add("message");
        } else {
            if (received.getMessage().getId() == 0) {
                missing.add("message.id");
            }
            if (received.getMessage().getUsername() == null) {
                missing.add("message.username");
            }
            if (received.getMessage().getText() == null) {
                missing.add("message.text");
            }
            if (received.getMessage().getTimestamp() == null) {
                missing.add("message.timestamp");
            }
        }
        if (received.getClient() == null) {
            missing.add("client");
        } else {
            if (received.getClient().getId() == null) {
                missing.add("client.id");
            }
        }
        if (missing.isEmpty()) {
            if (!received.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
                chatService.saveMessage(received.getMessage());
                IncomingMessage.post(received);
            }
            return new ResponseEntity<>(new StatusResponse("ok"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusResponse(missing), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/messages")
    public StatusResponse saveNewAndroidMessage(@RequestBody IncomingMessage received){
        if (received.getMessage() != null || received.getClient() != null){
            chatService.saveMessage(received.getMessage());
            return new StatusResponse("ok");
        }
        return new StatusResponse(new ArrayList<>(Arrays.asList("missing client", " and/or missing message")));

    }

    @GetMapping(value = "/api/messages")
    public MessageResponse receiveAndroidMessages(){
       return new MessageResponse(chatService.findAllMessages(), new Client());
    }
}

