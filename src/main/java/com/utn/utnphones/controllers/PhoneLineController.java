package com.utn.utnphones.controllers;
import java.util.ArrayList;
import java.util.List;

import com.utn.utnphones.exceptions.UserNotFoundException;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone-line")
public class PhoneLineController {
    private final PhoneLineService phoneLineService;
    private final UserService userService;

    @Autowired
    public PhoneLineController(final PhoneLineService phoneLineService, final UserService userService){
        this.phoneLineService = phoneLineService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<PhoneLine> getPhoneLines(){
        return this.phoneLineService.getPhoneLines();
    }

    @GetMapping("/{number}")
    public PhoneLine getPhoneLineByNumber(@PathVariable(value = "number", required = true) String number){
        return this.phoneLineService.getPhoneLineByNumber(number);
    }

    /*@GetMapping("/{number}/user")
    public User getUserByNumber(@PathVariable(value = "number", required = true)String number) throws UserNotFoundException {

        Integer idUser = this.phoneLineService.getUserIdByNumber(number);

        return this.userService.getUserById(idUser);
    }*/

    @GetMapping("/{number}/status")
    public LineStatus getStatusByNumber (@PathVariable(value = "number", required = true)String number){
        return this.phoneLineService.getStatusByNumber(number);
    }

    @GetMapping("/user={idUser}")
    public List<PhoneLine> getPhoneLinesByUser(@PathVariable(value = "id_user", required = true)Integer id_user){
        return phoneLineService.getPhoneLinesByUser(id_user);
    }

    @PostMapping("/")
    public void addPhoneLine(@RequestBody PhoneLine phoneLine){this.phoneLineService.addPhoneLine(phoneLine);}
}
