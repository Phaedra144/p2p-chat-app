package com.greenfox.szilvi.chatapp.model;

import lombok.Getter;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Getter
public class Client {

    String id;

    public Client() {
        this.id = System.getenv("CHAT_APP_UNIQUE_ID");
    }

    public String getId() {
        return id;
    }
}
