package com.utn.utnphones.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.repositories.CallRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.hibernate.JDBCException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    private final CallRepository callRepository;
    private final UserRepository userRepository;

    @Autowired
    public CallService(final CallRepository callRepository, UserRepository userRepository){
        this.callRepository = callRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<Call>> getUserCalls(Integer idClient) throws UserException {
        User u = new User();

        if((u = this.userRepository.getById(idClient)) == null){
            return (ResponseEntity<List<Call>>) Optional.ofNullable(null).orElseThrow(() -> new UserException("User not exists"));
        }

        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.getCallsFromByUser(idClient);

        if(!calls.isEmpty()){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<List<Locality>> getLocalitiesToByCallIdUser(Integer idUser) {

        List<Locality> localities = new ArrayList<Locality>();

        localities = this.callRepository.getLocalitiesToByCallIdUser(idUser);

        if(!localities.isEmpty()){
            return ResponseEntity.ok(localities);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<List<Call>> getCallsBtwDatesByUser(Integer idUser, String startDate, String finalDate) throws UserException {
        User u = new User();

        if((u = this.userRepository.getById(idUser)) == null){
            return (ResponseEntity<List<Call>>) Optional.ofNullable(null).orElseThrow(() -> new UserException("User not exists"));
        }

        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.getCallsBtwDatesByUser(idUser, startDate, finalDate);

        if(!calls.isEmpty()){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<List<Call>> getCallsBtwDates(String date1, String date2){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.getCallsBtwDates(date1, date2);

        if(!calls.isEmpty()){
            return ResponseEntity.ok(calls);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public ResponseEntity<Call> addCall(Call call) throws ValidationException {
        Call savedCall = new Call();

        try {
            return ResponseEntity.ok(savedCall = callRepository.save(call));
        }
        catch(Exception e){
            return (ResponseEntity<Call>) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Error. Check: Post-current date || " +
                    "Tariff do not exists for this call ||" +
                    " One of the numbers inserted do not exists "));

        }
    }

}
