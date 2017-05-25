package com.greenfox.szilvi.chatapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse {

    String status;
    String message;

    public StatusResponse() {
    }

    public StatusResponse(String status) {
        this.status = status;
    }

    public StatusResponse(ArrayList<String> missing) {
        status = "custom error";
        message = "Missing field(s): ";
        for (int i = 0; i < missing.size(); i++) {
            message += missing.get(i);
            if (i < missing.size() - 1) {
                message += ", ";
            }
        }
    }
}
