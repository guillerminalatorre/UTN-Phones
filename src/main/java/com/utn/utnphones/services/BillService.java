package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.User;
import com.utn.utnphones.repositories.BillRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;

    @Autowired
    public BillService(final BillRepository billRepository, UserRepository userRepository){
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<Bill>> getBillsByIdUser(Integer idUser) throws UserException {

        User u = new User();

        if((u = this.userRepository.getById(idUser)) == null){
            return (ResponseEntity<List<Bill>>) Optional.ofNullable(null).orElseThrow(() -> new UserException("User not exists"));
        }

        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.getBillsByIdUser(idUser);

        if (!bills.isEmpty()) {
            return ResponseEntity.ok(bills);

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    public ResponseEntity<List<Bill>> getBillsBtwDatesByIdUser(String startDate, String finalDate, Integer idUser) throws UserException {
        User u = new User();

        if((u = this.userRepository.getById(idUser)) == null){
            return (ResponseEntity<List<Bill>>) Optional.ofNullable(null).orElseThrow(() -> new UserException("User not exists"));
        }

        List<Bill> bills = new ArrayList<Bill>();

        bills =this.billRepository.findBillsBtwDatesByIdUser(startDate, finalDate, idUser);

        if(!bills.isEmpty()){
            return ResponseEntity.ok(bills);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<List<Bill>> getBillsBtwDates(String startDate, String finalDate){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findBillsBtwDates(startDate, finalDate);

        if(!bills.isEmpty()){
            return ResponseEntity.ok(bills);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
