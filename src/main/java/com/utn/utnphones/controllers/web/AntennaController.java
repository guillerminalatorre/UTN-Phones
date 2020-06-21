package com.utn.utnphones.controllers.web;

import com.utn.utnphones.controllers.CallController;
import com.utn.utnphones.dto.AntennaCall;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.net.URI;

@RestController
@RequestMapping("/antenna")
public class AntennaController {
    private final CallController callController;

    @Autowired
    public AntennaController(final CallController callController){
        this.callController = callController;
    }

    @PostMapping("/")
    public ResponseEntity addCall(@RequestHeader("Authorization") String sessionToken, @RequestBody AntennaCall call) throws ParseException {

        Call send = new Call(0, null, null ,call.getPhoneNumberFrom(),call.getPhoneNumberTo(),null,null, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(call.getDatee()),
                new SimpleDateFormat("HH:mm:ss").parse(call.getDuration()),null,null);

        Call saved = this.callController.addCall(send);

        return ResponseEntity.created(getLocation(saved)).build();
    }

    private URI getLocation(Call call) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/")
                .buildAndExpand(call.getIdCall())
                .toUri();
    }
}
