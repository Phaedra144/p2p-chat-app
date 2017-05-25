package com.greenfox.szilvi.chatapp.controller;

import com.greenfox.szilvi.chatapp.model.*;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/")
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
            chatService.saveUser(user);
        }
        return "index";
    }

    @RequestMapping(value = "/enter")
    public String register() {
        return "enter";
    }

    @PostMapping(value = "/postMessage")
    public String saveMessage(String username, String message){
        if (!message.isEmpty()) {
            Message msg = new Message(username, message);
            chatService.saveMessage(msg);
            IncomingMessage.post(new IncomingMessage(new Client(), msg));
        }
        return "redirect:/";
    }
}

