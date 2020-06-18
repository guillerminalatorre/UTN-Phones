package com.utn.utnphones.controllers.web;



import com.utn.utnphones.controllers.client.ClientController;
import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.exceptions.InvalidLoginException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    ClientController clientController;
    SessionManager sessionManager;

    @Autowired
    public LoginController(ClientController clientController, SessionManager sessionManager) {
        this.clientController = clientController;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) throws ValidationException, UserException, InvalidLoginException {

        ResponseEntity response;

        User u = clientController.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        String token = sessionManager.createSession(u);

        response = ResponseEntity.ok().headers(createHeaders(token)).build();


        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }

}
