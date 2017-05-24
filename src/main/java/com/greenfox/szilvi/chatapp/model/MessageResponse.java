package com.greenfox.szilvi.chatapp.model;

import javax.ws.rs.core.Response;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
public class MessageResponse {

    String status;
    String message;

    public MessageResponse(Message message, Client client){
        if(message != null){
            this.status = "ok";
        }else{
            throwError();
            this.status = "error";
            this.message = "Missing field(s): message.timestamp, client.id";
        }
    }

    public Response.ResponseBuilder throwError(){
        return Response.status(401);
    }
}
