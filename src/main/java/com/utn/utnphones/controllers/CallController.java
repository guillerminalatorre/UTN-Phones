package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Call;
import com.utn.utnphones.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;
    
    @Autowired
    public CallController(final CallService callService){
        this.callService = callService;
    }

    @GetMapping("/")
    public List<Call> getCalls(){
        return this.callService.getCalls();
    }

    @GetMapping("/{idCall}")
    public Call getCallById(@PathVariable(value = "idCall", required = true) Integer idCall){
        return this.callService.getCallById(idCall);
    }

    @GetMapping("/tariff={idTariff}")
    public List<Call> getCallsByTariff(@PathVariable(value = "idTariff", required = true) Integer idTariff){
        return this.callService.getCallsByTariff(idTariff);
    }

    @GetMapping("/bill={idBill}")
    public List<Call> getCallsByBill( @PathVariable(value = "idBill", required = true) Integer idBill){
        return this.callService.getCallsByBill(idBill);
    }

    @GetMapping("/linefrom={idPhoneLineFrom}")
    public List<Call> getCallsByLineFrom(@PathVariable(value = "idPhoneLineFrom", required = true) String phoneLineFrom){
        return this.callService.getCallsByLineFrom(phoneLineFrom);
    }

    @GetMapping("/linefrom={idPhoneLineTo}")
    public List<Call> getCallsByLineTo(@PathVariable(value = "idPhoneLineTo", required = true) String phoneLineTo){
        return this.callService.getCallsByLineTo(phoneLineTo);
    }

    @GetMapping("/date={date}")
    public List<Call> getCallsByDate(@PathVariable(value = "date", required = true) String date){
        return this.callService.getCallsByDate(date);
    }

    @GetMapping( "/between-dates/{startDate}/{finalDate}")
    public List<Call> getCallsBtwDates(@PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                            @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callService.getCallsBtwDates(startDate, finalDate);

        return calls;
    }

}
