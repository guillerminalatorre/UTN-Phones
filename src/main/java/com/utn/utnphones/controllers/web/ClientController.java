package com.utn.utnphones.controllers.web;


import com.utn.utnphones.controllers.BillController;
import com.utn.utnphones.controllers.CallController;
import com.utn.utnphones.controllers.UserController;
import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utn.utnphones.exceptions.ValidationException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final UserController userController;
    private final CallController callController;
    private final BillController billController;
    private final SessionManager sessionManager;

    @Autowired
    public ClientController(final UserController userController, CallController callController, BillController billController, final SessionManager sessionManager){
        this.userController = userController;
        this.callController = callController;
        this.billController = billController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }

    @GetMapping("/info")
    public ResponseEntity<User> update(@RequestHeader("Authorization") String sessionToken,
                                       @RequestBody LoginRequestDto client) throws UserException, ValidationException{
        User currentUser = getCurrentUser(sessionToken);

        return  this.userController.update(currentUser.getIdUser(), client);

    }

    @GetMapping("/calls")
    public ResponseEntity<List<Call>> getCalls(@RequestHeader("Authorization") String sessionToken) throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        return this.callController.getUserCalls(currentUser.getIdUser());
    }

    @GetMapping("/calls/most-called-places")
    public ResponseEntity<List<Locality>> getMost(@RequestHeader("Authorization") String sessionToken) throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        return this.callController.getLocalitiesToByCallIdUser(currentUser.getIdUser());
    }

    @GetMapping( "/calls/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Call>> getCallsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                       @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        return this.callController.getCallsBtwDatesByUser(startDate, finalDate, currentUser.getIdUser());

    }

    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getBills(@RequestHeader("Authorization") String sessionToken) throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        return this.billController.getBillsByIdUser(currentUser.getIdUser());

    }

    @GetMapping("/bills/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Bill>> getBillsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) String startDate,
                                                       @PathVariable(value = "finalDate", required = true) String finalDate) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return this.billController.getBillsBtwDatesByIdUser( startDate, finalDate, currentUser.getIdUser());


    }

    private User getCurrentUser(String sessionToken) throws UserException {

        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken))
                .orElseThrow(() -> new UserException("User not Logged"));
    }
}

