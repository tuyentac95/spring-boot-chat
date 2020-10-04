package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username){
        System.out.println("check register: " + username);
        boolean isExits = false;
        List<User> users = userService.userList();
        for(User user : users){
            if (user.getUsername().equals(username)){
                isExits = true;
                break;
            }
        }
        if(isExits) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
