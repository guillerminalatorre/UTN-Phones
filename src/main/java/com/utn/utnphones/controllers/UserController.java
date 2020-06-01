package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.UserNotFoundException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> getUserById (@PathVariable(value = "idUser", required = true)Integer idUser) throws UserNotFoundException {

        return this.userService.getUserById(idUser);
    }

    /*ESTO NO SE VA A QUEDAR*/
    @GetMapping("/{idUser}/pass}")
    public String getPassById(@PathVariable(value = "idUser", required = true)Integer idUser)
    {
        return this.userService.getPassById(idUser);
    }

    @RequestMapping(value = "/controller", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity sendViaResponseEntity() {
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity sendViaException() {
        throw new UserNotFoundException(0);
    }
}
