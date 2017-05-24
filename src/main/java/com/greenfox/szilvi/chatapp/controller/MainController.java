package com.greenfox.szilvi.chatapp.controller;

import com.greenfox.szilvi.chatapp.model.RequestLogger;
import com.greenfox.szilvi.chatapp.model.User;
import com.greenfox.szilvi.chatapp.repository.MessageRepo;
import com.greenfox.szilvi.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
@Controller
public class MainController {

    @Autowired
    RequestLogger requestLogger;

    @Autowired
    ChatService chatService;

    @ModelAttribute
    public void log(HttpServletRequest request) {
        requestLogger.receiveLogInfo(request);
    }

    @RequestMapping(value = "/")
    public String home(Model model) {
        List<User> userList = chatService.getUsers();
        if (userList.isEmpty()) {
            return "redirect:/enter";
        }
        model.addAttribute("user", userList.get(0));
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
}

