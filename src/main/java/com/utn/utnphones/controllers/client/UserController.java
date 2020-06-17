package com.utn.utnphones.controllers.client;


import com.utn.utnphones.exceptions.InvalidLoginException;
import com.utn.utnphones.exceptions.UserNotexistException;
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
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserController(final UserService userService, SessionManager sessionManager){
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    /**
     *
     * @param username
     * @param password
     * @return User
     * @throws UserNotexistException (the user do not exists, or is not logged)
     * @throws ValidationException (some param is null)
     */
    public User login(String username, String password) throws UserNotexistException, ValidationException, InvalidLoginException {
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

    /**
     *
     * @param sessionToken
     * @return 200 0K + UserInfo
     * @throws UserNotexistException (if the user is not logged or do not exists)
     */
    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserNotexistException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }

    /**
     *
     * @param sessionToken
     * @return Current User
     * @throws UserNotexistException (if the user is not logged or do not exists)
     */
    private User getCurrentUser(String sessionToken) throws UserNotexistException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotexistException::new);
    }
}

