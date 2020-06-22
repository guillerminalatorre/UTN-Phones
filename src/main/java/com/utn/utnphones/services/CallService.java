package com.utn.utnphones.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.repositories.CallRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.validation.ValidationException;

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

    public Call addCall(Call call) {
        Call savedCall = new Call();

        savedCall = callRepository.save(call);

        return savedCall;
    }

}
