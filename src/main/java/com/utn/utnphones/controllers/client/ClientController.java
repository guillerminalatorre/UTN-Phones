package com.utn.utnphones.controllers.client;


import com.utn.utnphones.exceptions.InvalidLoginException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class ClientController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public ClientController(final UserService userService, SessionManager sessionManager){
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    public User login(String username, String password) throws UserException, ValidationException, InvalidLoginException {
        if ((username != null) && (password != null)) {

            User u = userService.login(username, password);

            if(sessionManager.userIsLogged(u)){
                throw new InvalidLoginException("This user is already logged");
            }
            else {
                return u;
            }
        } else {
            throw new ValidationException("Username and password must have a value");
        }
    }

    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }

    private User getCurrentUser(String sessionToken) throws UserException {

        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken))
                .orElseThrow(() -> new UserException("User not Logged"));
    }
}

