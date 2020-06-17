package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.UserNotFoundException;
import com.utn.utnphones.exceptions.UserNotexistException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserrController {
    private final UserService userService;

    @Autowired
    public UserrController(final UserService userService){
        this.userService = userService;
    }

    public User login(String username, String password) throws UserNotexistException, ValidationException {
        if ((username != null) && (password != null)) {
            return userService.login(username, password);
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById (@PathVariable(value = "idUser", required = true)Integer idUser) throws UserNotFoundException {

        return this.userService.getUserById(idUser);
    }

    @GetMapping("/{idUser}/pass}")
    public String getPassById(@PathVariable(value = "idUser", required = true)Integer idUser)
    {
        return this.userService.getPassById(idUser);
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user){this.userService.addUser(user);}
}
