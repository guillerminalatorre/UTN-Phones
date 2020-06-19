package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.BillService;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;
    private final PhoneLineService phoneLineService;
    private final UserService userService;

    @Autowired
    public BillController(final BillService billService, final PhoneLineService phoneLineService, final UserService userService){
        this.billService = billService;
        this.phoneLineService = phoneLineService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Bill> getBills(){
        return this.billService.getBills();
    }

    @GetMapping("/{idBill}")
    public Bill getBillById(@PathVariable(value = "idBill", required = true) Integer idBill){
        return this.billService.getBillById(idBill);
    }

    @GetMapping("/paid")
    public List<Bill> getBillsPaid(){
        return this.billService.getBillsPaid();
    }

    @GetMapping("/unpaid")
    public List<Bill> getBillsUnpaid(){
        return this.billService.getBillsUnpaid();
    }

    @GetMapping("/number={phoneNumber}")
    public List<Bill> getBillsByPhoneNumber(@PathVariable(value = "phoneNumber", required = true) String phoneNumber){
        return this.billService.getBillsByPhoneNumber(phoneNumber);
    }

    @GetMapping("/date={date}")
    public List<Bill> getBillsFromDate(@PathVariable(value = "date", required = true) String date){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billService.getBillsFromDate(date);

        return bills;
    }

    @GetMapping("/sdate={startDate}&fdate{finalDate}")
    public List<Bill> getBillsBtwDates(@PathVariable(value = "startDate", required = true) String startDate, @PathVariable(value = "finalDate", required = true) String finalDate){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billService.getBillsBtwDates(startDate, finalDate);

        return bills;
    }

    /*@GetMapping("/{idBill}/user")
    public User getUserById(@PathVariable(value = "idBill", required = true) Integer idBill){
        String phoneNumber = this.billService.getNumberById(idBill);

        Integer idUser = this.phoneLineService.getUserIdByNumber(phoneNumber);

        return this.userService.getUserById(idUser);

    }*/

    @PostMapping("/")
    public void addBill(@RequestBody Bill bill){this.billService.addBill(bill);}


}
