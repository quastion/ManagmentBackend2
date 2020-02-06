package com.opencode.managment.controller;

import com.opencode.managment.app.Response;
import com.opencode.managment.entity.User;
import com.opencode.managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public void setService(UserService service){
        this.service = service;
    }

    @PostMapping("/login")
    public Response login(@RequestBody User entity){
        return service.login(entity);
    }

}
