package com.greenfox.szilvi.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Szilvi on 2017. 05. 25..
 */
public class MessageResponse {

    List<Message> messages;
    Client client;

    public MessageResponse(List<Message> messages, Client client) {
        this.messages = messages;
        this.client = client;
    }

    public MessageResponse() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Client getClient() {
        return client;
    }
}
