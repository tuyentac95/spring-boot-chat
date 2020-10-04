package com.codegym.controller;

import com.codegym.model.Message;
import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExits = false;
        List<User> users = userService.userList();
        for(User user : users){
            if (user.getUsername().equals(to)){
                isExits = true;
                break;
            }
        }
        System.out.println(isExits);
        if(isExits){
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }

    @MessageMapping("/notify/{username}")
    public void notifyOnline(@DestinationVariable String username,User user){
        List<String> onlineUsers = userService.getOnlineUsers();
        for (String onlineUser : onlineUsers){
            if(onlineUser.equals(username)) continue;
            System.out.println(username + " is online");
            simpMessagingTemplate.convertAndSend("/topic/online/" + onlineUser, user);
        }
    }
}
