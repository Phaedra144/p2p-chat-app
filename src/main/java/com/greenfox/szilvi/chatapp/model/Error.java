package com.greenfox.szilvi.chatapp.model;

import lombok.Getter;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
public class Error {
    String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
