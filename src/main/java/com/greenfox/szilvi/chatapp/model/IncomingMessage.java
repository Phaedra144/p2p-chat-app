package com.greenfox.szilvi.chatapp.model;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Szilvi on 2017. 05. 24..
 */
@Getter
public class IncomingMessage {

    Client client;
    Message message;

    public IncomingMessage() {

    }

    public IncomingMessage(Client client, Message message) {
        this.client = client;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public Client getClient() {
        return client;
    }
}
