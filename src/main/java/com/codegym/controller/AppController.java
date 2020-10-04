package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    public ModelAndView homePage(
            @RequestParam("username") String username,
            @RequestParam("password") String password){
        ModelAndView modelAndView;
        List<User> userList = userService.userList();
        for (User user : userList){
            System.out.println(user);
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                modelAndView = new ModelAndView("/home");
                modelAndView.addObject("user",user);
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }

    @GetMapping("/start-chat")
    public ModelAndView startChat(@RequestParam("username") String username){
        ModelAndView modelAndView = new ModelAndView("/demo-chat");
        modelAndView.addObject("username",username);
        List<User> chattingList = userService.chattingList(username);
        modelAndView.addObject("chattingList",chattingList);
        return modelAndView;
    }
}
