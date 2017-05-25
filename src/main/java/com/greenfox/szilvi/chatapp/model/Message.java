package com.greenfox.szilvi.chatapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Entity
public class Message {

    @Id
    long id;
    String username;
    String text;
    Timestamp timestamp;

    public Message() {
    }

    public Message(String username, String text) {
        this.id = randomGenerateId();
        this.username = username;
        this.text = text;
        this.timestamp = Timestamp.from(Instant.now());
    }

    private long randomGenerateId() {
        return (long) (Math.random()*9999999 + 1000000);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
