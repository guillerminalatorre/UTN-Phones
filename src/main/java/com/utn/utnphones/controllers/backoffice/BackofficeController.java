package com.utn.utnphones.controllers.backoffice;

import com.utn.utnphones.dto.UserDto;
import com.utn.utnphones.exceptions.UserAlreadyExistsException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.UserService;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.ValidationException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public BackofficeController(final UserService userService, SessionManager sessionManager){
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }

    @PutMapping("/")
    public ResponseEntity<User> update(@RequestHeader("Authorization") String sessionToken,
                                       @RequestBody UserDto user) throws ValidationException, UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(this.userService.update(currentUser.getIdUser(), user));
    }

    //MANEJO DE CLIENTES

    @PostMapping("/users/add")
    public ResponseEntity addUser(@RequestHeader("Authorization") String sessionToken, @RequestBody UserDto userDto) throws UserException, UserAlreadyExistsException, ValidationException {
        getCurrentUser(sessionToken);

        User user = this.userService.createUser(userDto);

        return ResponseEntity.created(getLocation(user)).build();
    }

    @DeleteMapping("/users/{idUser}")
    public ResponseEntity deleteUser(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer idUser) throws UserException {
        getCurrentUser(sessionToken);

        this.userService.delete(idUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{idClient}")
    public ResponseEntity<User> updateClient(@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable Integer idClient,
                                             @RequestBody UserDto userDto) throws ValidationException, UserException {
        getCurrentUser(sessionToken);

        return ResponseEntity.ok(this.userService.update(idClient, userDto));
    }

    private User getCurrentUser(String sessionToken) throws UserException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(() -> new UserException("User not logged"));
    }

    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/")
                .buildAndExpand(user.getIdUser())
                .toUri();
    }

}
