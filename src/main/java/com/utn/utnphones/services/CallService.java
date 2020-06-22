package com.utn.utnphones.services;

import java.util.ArrayList;
import java.util.List;

import com.utn.utnphones.exceptions.CallByLocalityToNotFound;
import com.utn.utnphones.exceptions.IdLtyFromTariffsNotFoundException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.repositories.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    private final CallRepository callRepository;

    @Autowired
    public CallService (final CallRepository callRepository){
        this.callRepository = callRepository;
    }

    public List<Call> getCalls(){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findAll();

        return calls;
    }

    public Call getCallById(Integer idCall){
        Call call = new Call();

        call = this.callRepository.findById(idCall).get();

        return call;
    }

    public List<Call> getCallsByTariff( Integer idTariff){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByTariff(idTariff);

        return calls;
    }

    public List<Call> getCallsByBill( Integer idBill){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByBill(idBill);

        return calls;
    }

    public List<Call> getCallsByLineFrom(String phoneLineFrom){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByLineFrom( phoneLineFrom );

        return calls;
    }

    public List<Call> getCallsByLineTo(String phoneLineTo) throws CallByLocalityToNotFound{
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByLineTo(phoneLineTo);

        if(calls.isEmpty()) {

            throw new CallByLocalityToNotFound(phoneLineTo);
        }

        return calls;
    }

    public List<Call> getCallsByDate(String date){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByDate( date );

        return calls;
    }

    public List<Call> getCallsBtwDates(String date1, String date2){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.getCallsBtwDates(date1, date2);

        return calls;
    }

    public Call addCall(Call call) {
        Call savedCall =  callRepository.save(call);
        return savedCall;
    }

    public List<Call> getCallsBtwDatesByUser(Integer idUser, String startDate, String finalDate) {
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.getCallsBtwDatesByUser(idUser, startDate, finalDate);

        return calls;
    }

    public List<Call> getCallsFromByUser(Integer idUser){
        return this.callRepository.getCallsFromByUser(idUser);
    }
}
