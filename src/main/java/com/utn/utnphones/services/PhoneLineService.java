package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.repositories.PhoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneLineService {
    private final PhoneLineRepository phoneLineRepository;

    @Autowired
    public PhoneLineService (final PhoneLineRepository phoneLineRepository){
        this.phoneLineRepository = phoneLineRepository;
    }

    public List<PhoneLine> getPhoneLines(){
        List<PhoneLine> phonelines = new ArrayList<PhoneLine>();

        phonelines = phoneLineRepository.findAll();

        return phonelines;
    }

    public PhoneLine getPhoneLineByNumber(String number){
        PhoneLine phoneline = new PhoneLine();

        phoneline = phoneLineRepository.findById(number).get();

        return phoneline;
    }

    public Integer getUserIdByNumber (String number){
        return phoneLineRepository.findUserByNumber(number);
    }

    public LineStatus getStatusByNumber(String phone_number){
        return phoneLineRepository.findStatusByNumber(phone_number);
    }

    public List<PhoneLine> getPhoneLinesByUser(Integer id_user){
        List<PhoneLine> phonelines = new ArrayList<PhoneLine>();

        phonelines = phoneLineRepository.findByUser(id_user);

        return phonelines;
    }

}
