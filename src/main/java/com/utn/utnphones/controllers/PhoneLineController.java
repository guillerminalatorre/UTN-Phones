package com.utn.utnphones.controllers;
import java.util.List;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.Status;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PhoneLine getPhoneLineByNumber(String number){
        return this.phoneLineService.getPhoneLineByNumber(number);
    }

    @GetMapping("/{number}/user")
    public User getUserByNumber(String number){

        Integer idUser = this.phoneLineService.getUserIdByNumber(number);

        return this.userService.getUserById(idUser);
    }

    @GetMapping("/-status={number}")
    public Status getStatusByNumber (String number){
        return this.phoneLineService.getStatusByNumber(number);
    }
}
