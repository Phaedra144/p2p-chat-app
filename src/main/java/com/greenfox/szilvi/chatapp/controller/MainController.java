package com.greenfox.szilvi.chatapp.controller;

import com.greenfox.szilvi.chatapp.model.*;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Controller
public class MainController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    ChatService chatService;

    @ExceptionHandler
    public void error(Exception ex) {
        requestLogger.error(ex.getMessage());
    }

    @ModelAttribute
    public void log(HttpServletRequest request) {
        requestLogger.info(request);
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        if (chatService.getUsers().isEmpty()) {
            return "redirect:/enter";
        }
        model.addAttribute("user", chatService.getUser());
        model.addAttribute("messages", chatService.findAllMessages());
        return "index";
    }

    @PostMapping("/")
    public String changeUser(@ModelAttribute User user, Model model) {
        if (user.getUsername().isEmpty()) {
            model.addAttribute("error", "The username field is empty");
        } else {
            user.setId(1l);
            chatService.saveUser(user);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/enter")
    public String register() {
        return "enter";
    }

    @PostMapping(value = "/postMessage")
    public String saveMessage(String username, String message){
        if (!message.isEmpty()) {
            Message msg = new Message(username, message);
            chatService.saveMessage(msg);
            ChatService.post(new IncomingMessage(new Client(), msg));
        }
        return "redirect:/";
    }
}

