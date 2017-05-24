//package com.greenfox.szilvi.chatapp.model;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//
///**
// * Created by Szilvi on 2017. 05. 18..
// */
//public class SampleRestTemp {
//    String url = "https://limitless-thicket-98020.herokuapp.com/posts";
//    RestTemplate restTemplate = new RestTemplate();
//
//    @GetMapping("/add")
//    public Message index(
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "href") String href) {
//
//        Message p = new Message(title, href);
//
//        Message newMessage = restTemplate.postForObject(url, p, Message.class);
//        return newMessage;
//    }
//}
