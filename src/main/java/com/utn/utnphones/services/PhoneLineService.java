package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.GoneException;
import com.utn.utnphones.exceptions.PhoneLineNotExistsException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.repositories.LineTypeRepository;
import com.utn.utnphones.repositories.PhoneLineRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhoneLineService {
    private final PhoneLineRepository phoneLineRepository;
    private final UserRepository userRepository;
    private final LineTypeRepository lineTypeRepository;

    @Autowired
    public PhoneLineService(final PhoneLineRepository phoneLineRepository, UserRepository userRepository, LineTypeRepository lineTypeRepository){
        this.phoneLineRepository = phoneLineRepository;
        this.userRepository = userRepository;
        this.lineTypeRepository = lineTypeRepository;
    }

    public PhoneLine add(PhoneLineDto phoneLineDto) throws ValidationException, UserException {
        PhoneLine saved = new PhoneLine();

        if(!phoneLineDto.getLineStatus().equals(LineStatus.ENABLED.toString())) {
            if (!phoneLineDto.getLineStatus().equals(LineStatus.DISABLED.toString())) {
                if (!phoneLineDto.getLineStatus().equals(LineStatus.SUSPENDED.toString())) {
                    return (PhoneLine) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Line Status is not valid"));

                }else{
                    saved.setStatus(LineStatus.SUSPENDED);
                }
            }else{
                saved.setStatus(LineStatus.DISABLED);
            }
        }else{
            saved.setStatus(LineStatus.ENABLED);
        }

        LineType lineType = new LineType();

        lineType = this.lineTypeRepository.getById(phoneLineDto.getIdLineType());

        if(lineType == null){
            return (PhoneLine) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("LineType is not valid"));
        }

        User user = new User();

        user = this.userRepository.getById(phoneLineDto.getIdUser());

        if(user == null){
            return (PhoneLine) Optional.ofNullable(null).orElseThrow(() -> new UserException("User do not Exists"));
        }

        saved.setLineType(lineType);
        saved.setPhoneNumber(phoneLineDto.getPhoneNumber());
        saved.setUser(user);

        return this.phoneLineRepository.save(saved);
    }

    public PhoneLine update(PhoneLine phoneLine) throws PhoneLineNotExistsException {

        return this.phoneLineRepository.save(phoneLine);

    }

    public List<PhoneLine> getPhoneLines(){
        List<PhoneLine> phonelines = new ArrayList<PhoneLine>();

        phonelines = phoneLineRepository.findAll();

        return phonelines;
    }

    public PhoneLine getById(Integer idPhoneLine) throws PhoneLineNotExistsException, GoneException {
        PhoneLine phoneLine = new PhoneLine();

        phoneLine= this.phoneLineRepository.getById(idPhoneLine);

        if(phoneLine==null){
            return (PhoneLine) Optional.ofNullable(null)
                    .orElseThrow(() -> new PhoneLineNotExistsException("Phone Line do not exists"));
        }
        else if (phoneLine.getActive() == false){
            return (PhoneLine) Optional.ofNullable(null)
                    .orElseThrow(() -> new GoneException("Phone Line has been deleted"));
        }

        return phoneLine;
    }

    public PhoneLine getPhoneLineByNumber(String number) throws PhoneLineNotExistsException, GoneException {

        PhoneLine phoneLine = new PhoneLine();

        phoneLine= this.phoneLineRepository.getByPhoneNumber(number);

        if(phoneLine==null){
            return (PhoneLine) Optional.ofNullable(null)
                    .orElseThrow(() -> new PhoneLineNotExistsException("Phone Line do not exists"));
        }
        else if (phoneLine.getActive() == false){
            return (PhoneLine) Optional.ofNullable(null)
                    .orElseThrow(() -> new GoneException("Phone Line has been deleted"));
        }

        return phoneLine;
    }

}
