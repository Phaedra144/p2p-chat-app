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
    Timestamp date;

    public Message(String title, String href) {
    }
}
