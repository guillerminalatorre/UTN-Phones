package com.utn.utnphones.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Call> getCallsByLineTo(String phoneLineTo){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByLineTo( phoneLineTo );

        return calls;
    }

    public List<Call> getCallsByDate(String date){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findByDate( date );

        return calls;
    }

    public List<Call> getCallsBtwDates(String date1, String date2){
        List<Call> calls = new ArrayList<Call>();

        calls = this.callRepository.findCallsBtwDates(date1, date2);

        return calls;
    }

    public void addCall(Call call) {
        this.callRepository.save(call);
    }

    //Prueba lab v
    public List<Call> getBtwPrefix() {
        List<Call>  calls =this.callRepository.getBtwPrefix();
                List<Call> calls2 = this.callRepository.getOtherPrefix();
        return Stream.concat(calls.stream(), calls2.stream())
                .collect(Collectors.toList());
    }
}
