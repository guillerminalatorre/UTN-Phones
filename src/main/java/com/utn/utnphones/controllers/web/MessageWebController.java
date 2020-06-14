package com.utn.utnphones.controllers.web;
/*

import edu.utn.mail.controller.MessageController;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageWebController {

    private final MessageController messageController;
    private final SessionManager sessionManager;

    @Autowired
    public MessageWebController(MessageController messageController, SessionManager sessionManager) {
        this.messageController = messageController;
        this.sessionManager = sessionManager;
    }


    @GetMapping
    public ResponseEntity<List<Message>> getMessages(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to) throws ParseException, UserNotexistException {
        User currentUser = getCurrentUser(sessionToken);
        List<Message> messages = new ArrayList<>();
        if ((from != null) && (to != null)) {
            Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
            Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);
            messages = messageController.getMessagesByDate(currentUser, fromDate, toDate);
        } else {
            messages = messageController.getMessages(currentUser);
        }
        return (messages.size() > 0) ? ResponseEntity.ok(messages) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@RequestHeader("Authorization") String sessionToken, @PathVariable("id") Long messageId) throws UserNotexistException {
        User currentUser = getCurrentUser(sessionToken);

        Message message = messageController.getMessage(messageId);

        ResponseEntity<Message> responseEntity;
        if (message != null) {
            if ((message.getFrom().getUserId().equals(currentUser.getUserId()) || (message.getTo().getUserId().equals(currentUser.getUserId())))) {
                responseEntity = ResponseEntity.ok(message);
            } else {
                throw new UserNotexistException();
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }


    @PostMapping
    public ResponseEntity newMessage(@RequestHeader("Authorization") String sessionToken, @RequestBody Message message) throws UserNotexistException {
        User currentUser = getCurrentUser(sessionToken);
        message.setFrom(currentUser);
        Message newMessage = messageController.newMessage(message);
        return ResponseEntity.created(getLocation(newMessage)).build();
    }

    private User getCurrentUser(String sessionToken) throws UserNotexistException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotexistException::new);
    }

    private URI getLocation(Message message) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getMessageId())
                .toUri();
    }
}
*/