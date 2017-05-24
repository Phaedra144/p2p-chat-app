package com.greenfox.szilvi.chatapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Entity
public class Message {

    @Id
    int id;
    String username;
    String text;
    Timestamp timestamp;

    public Message() {
    }

    public Message(String username, String text, Timestamp timestamp) {
        this.id = randomGenerateId();
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }

    private int randomGenerateId() {
        return (int) (Math.random()*9999999 + 1000000);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public Timestamp getDate() {
        return timestamp;
    }
}
