package com.utn.utnphones.controllers;

import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.dto.UpdateUserDto;
import com.utn.utnphones.dto.NewUserDto;
import com.utn.utnphones.exceptions.*;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }


    public User login(String username, String password, SessionManager sessionManager) throws UserException, ValidationException, InvalidLoginException {
        if ((username != null) && (password != null)) {

            User u = userService.login(username, password);

            if(sessionManager.userIsLogged(u)){
                return (User) Optional.ofNullable(null).orElseThrow(() -> new InvalidLoginException("This user is already logged"));
            }
            else {
                return u;
            }
        } else {
            return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Username and password must have a value"));
        }
    }

    public ResponseEntity<User> update(Integer idUser, UpdateUserDto user) throws ValidationException, UserException {

        return ResponseEntity.ok(this.userService.update( idUser , user));
    }

    public ResponseEntity<User> update(Integer idClient, LoginRequestDto user) throws ValidationException, UserException{

        return ResponseEntity.ok(this.userService.update(idClient, user));
    }

    public ResponseEntity<User> add(NewUserDto userDto) throws UserAlreadyExistsException, ValidationException {
        return ResponseEntity.created(getLocation(this.userService.add(userDto))).build();
    }

    public void delete(Integer idUser) throws UserException {
        this.userService.delete(idUser);
    }

    public ResponseEntity<User> getUserById (Integer idUser) throws UserException {

        return ResponseEntity.ok(this.userService.getUserById(idUser));
    }

    public ResponseEntity<List<User>> getUsersActive(){

        return this.userService.getUsersActive();
    }

    public ResponseEntity<List<User>> getUsersDisabled(){

        return this.userService.getUsersDisabled();
    }

    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/")
                .buildAndExpand(user.getIdUser())
                .toUri();
    }


}
