package com.utn.utnphones.controllers;

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.GoneException;
import com.utn.utnphones.exceptions.PhoneLineNotExistsException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

import java.net.URI;
import java.util.Optional;

@Controller
public class PhoneLineController {
    private final PhoneLineService phoneLineService;
    private final UserService userService;

    @Autowired
    public PhoneLineController(final PhoneLineService phoneLineService, final UserService userService){
        this.phoneLineService = phoneLineService;
        this.userService = userService;
    }

    public ResponseEntity add(@RequestBody PhoneLineDto phoneLine) throws ValidationException, UserException {

        return ResponseEntity.created(getLocation(this.phoneLineService.add(phoneLine))).build();
    }

    private URI getLocation(PhoneLine phoneLine) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/")
                .buildAndExpand(phoneLine.getIdPhoneLine())
                .toUri();
    }

    public ResponseEntity<PhoneLine> changeStatus(Integer idPhoneLine, String status) throws PhoneLineNotExistsException, GoneException, ValidationException {
        PhoneLine phoneLine = this.phoneLineService.getById(idPhoneLine);

        if(phoneLine == null) {
            return (ResponseEntity<PhoneLine>) Optional.ofNullable(null).orElseThrow(() -> new PhoneLineNotExistsException("Phone Line do not exists"));
        }

        if(!status.equals("disable")){
                if(!status.equals("enable")){
                        if(!status.equals("suspend")){
                            return (ResponseEntity<PhoneLine>) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Path \"status\" is not valid"));
                        }else{
                            phoneLine.setStatus(LineStatus.SUSPENDED);
                        }
                }else{
                    phoneLine.setStatus(LineStatus.ENABLED);
                }
        }else{
            phoneLine.setStatus(LineStatus.DISABLED);
        }

        return ResponseEntity.ok(this.phoneLineService.update(phoneLine));
    }


    public ResponseEntity<PhoneLine> delete(Integer idPhoneLine) throws PhoneLineNotExistsException, GoneException {

        PhoneLine phoneLine = this.phoneLineService.getById(idPhoneLine);

        phoneLine.setActive(false);

        return ResponseEntity.ok(this.phoneLineService.update(phoneLine));
    }

    public ResponseEntity<PhoneLine> getPhoneLineByNumber(String number) throws PhoneLineNotExistsException, GoneException {
        return ResponseEntity.ok(this.phoneLineService.getPhoneLineByNumber(number));
    }

    public ResponseEntity<List<PhoneLine>> getPhoneLines(){
        return ResponseEntity.ok(this.phoneLineService.getPhoneLines());
    }

}
