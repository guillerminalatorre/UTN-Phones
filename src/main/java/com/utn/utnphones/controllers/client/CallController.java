package com.utn.utnphones.controllers.client;

import com.utn.utnphones.exceptions.CallByLocalityToNotFound;
import com.utn.utnphones.exceptions.UserNotexistException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.CallService;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/call")
public class CallController {
    private final CallService callService;
    private final SessionManager sessionManager;

    @Autowired
    public CallController(final CallService callService, final SessionManager sessionManager){
        this.callService = callService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public ResponseEntity<List<Call>> getCalls(@RequestHeader("Authorization") String sessionToken) throws UserNotexistException {

        User currentUser = getCurrentUser(sessionToken);

        List<Call> calls = new ArrayList<Call>();

        calls = this.callService.getCallsFromByUser(currentUser.getIdUser());

        if(calls != null){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping( "/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Call>> getCallsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                       @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserNotexistException {

        User currentUser = getCurrentUser(sessionToken);

        List<Call> calls = new ArrayList<Call>();

        calls = this.callService.getCallsBtwDatesByUser(currentUser.getIdUser(), startDate, finalDate);

        if(calls != null){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private User getCurrentUser(String sessionToken) throws UserNotexistException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotexistException::new);
    }
}
