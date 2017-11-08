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

    public Message getMessage() {
        return message;
    }

    public Client getClient() {
        return client;
    }
}
