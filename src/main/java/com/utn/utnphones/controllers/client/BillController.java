package com.utn.utnphones.controllers.client;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.User;
import com.utn.utnphones.services.BillService;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    private final BillService billService;
    private final PhoneLineService phoneLineService;
    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public BillController(final BillService billService, final PhoneLineService phoneLineService, final UserService userService, final SessionManager sessionManager) {
        this.billService = billService;
        this.phoneLineService = phoneLineService;
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bill>> getBills(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);


        List<Bill> bills = this.billService.getBillsByIdUser(currentUser.getIdUser());


        if (!bills.isEmpty()) {
            return ResponseEntity.ok(bills);

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    @GetMapping("/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Bill>> getBillsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                       @PathVariable(value = "startDate", required = true) String startDate,
                                       @PathVariable(value = "finalDate", required = true) String finalDate) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billService.getBillsBtwDatesByIdUser( startDate, finalDate, currentUser.getIdUser());

        if(!bills.isEmpty()){
            return ResponseEntity.ok(bills);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private User getCurrentUser(String sessionToken) throws UserException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(() -> new UserException("User not logged"));
    }

}