package com.utn.utnphones.controllers.client;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.CallService;
import com.utn.utnphones.services.LocalityService;
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
    private final LocalityService localityService;
    private final SessionManager sessionManager;

    @Autowired
    public CallController(final CallService callService, final SessionManager sessionManager, final LocalityService localityService){
        this.callService = callService;
        this.sessionManager = sessionManager;
        this.localityService = localityService;
    }

    @GetMapping("/")//PROBADO SIN TEST
    public ResponseEntity<List<Call>> getCalls(@RequestHeader("Authorization") String sessionToken) throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        List<Call> calls = new ArrayList<Call>();

        calls = this.callService.getCallsFromByUser(currentUser.getIdUser());

        if(!calls.isEmpty()){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/most-called-places/")
    public ResponseEntity<List<Locality>> getMost(@RequestHeader("Authorization") String sessionToken) throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        List<Locality> localities = new ArrayList<Locality>();

        localities = this.localityService.getLocalitiesToByCallIdUser(currentUser.getIdUser());

        if(!localities.isEmpty()){
            return ResponseEntity.ok(localities);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping( "/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Call>> getCallsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                       @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserException {

        User currentUser = getCurrentUser(sessionToken);

        List<Call> calls = new ArrayList<Call>();

        calls = this.callService.getCallsBtwDatesByUser(currentUser.getIdUser(), startDate, finalDate);

        if(!calls.isEmpty()){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private User getCurrentUser(String sessionToken) throws UserException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(() -> new UserException("User not logged"));
    }
}
