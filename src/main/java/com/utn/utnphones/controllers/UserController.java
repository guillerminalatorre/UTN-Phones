package com.utn.utnphones.controllers;

import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }


    @GetMapping("/")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/{idUser}")
    public User getUserById (Integer idUser){
        return this.userService.getUserById(idUser);
    }

    @GetMapping("/-pass={idUser}")
    public String getPassById(Integer idUser)
    {
        return this.userService.getPassById(idUser);
    }
}
