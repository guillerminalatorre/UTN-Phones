package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CallController {
    private final CallService callService;
    
    @Autowired
    public CallController(final CallService callService){
        this.callService = callService;
    }

    public ResponseEntity<List<Call>> getUserCalls(Integer idClient) throws UserException {
        return this.callService.getUserCalls(idClient);
    }

    public ResponseEntity<List<Locality>> getLocalitiesToByCallIdUser(Integer idUser) {
        return this.callService.getLocalitiesToByCallIdUser(idUser);
    }

    public ResponseEntity<List<Call>> getCallsBtwDatesByUser(String startDate, String finalDate, Integer idUser)
            throws UserException {

        return this.callService.getCallsBtwDatesByUser(idUser, startDate, finalDate);
    }

    public ResponseEntity<List<Call>> getCallsBtwDates(String startDate, String finalDate)
            throws UserException {

        return this.callService.getCallsBtwDates(startDate, finalDate);
    }


    public Call addCall(Call call){
        return this.callService.addCall(call);
    }


}
