package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.GoneException;
import com.utn.utnphones.exceptions.PhoneLineNotExistsException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.exceptions.UserException;;
import com.utn.utnphones.models.LineType;
=======
>>>>>>> parent of 5e7514a... Merge branch 'UTN-Phones-B1'
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.repositories.PhoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.dao.DataIntegrityViolationException;
=======
>>>>>>> parent of 5e7514a... Merge branch 'UTN-Phones-B1'
import org.springframework.stereotype.Service;

@Service
public class PhoneLineService {
    private final PhoneLineRepository phoneLineRepository;

    @Autowired
    public PhoneLineService (final PhoneLineRepository phoneLineRepository){
        this.phoneLineRepository = phoneLineRepository;
<<<<<<< HEAD
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

        LineType lineType = this.lineTypeRepository.getById(phoneLineDto.getIdLineType());

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

        PhoneLine save = new PhoneLine();

        try {
            save = phoneLineRepository.save(saved);
        }catch(DataIntegrityViolationException e){
            return  (PhoneLine) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Phone number already exists"));
        }catch(Exception e){
            return (PhoneLine) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Error. Check: Phone number do not contains a prefix valid || " +
                    "Digits quantity is not the same as Line Type "));
        }

        return save;
    }

    public PhoneLine update(PhoneLine phoneLine) throws PhoneLineNotExistsException {

        return this.phoneLineRepository.save(phoneLine);

    }

    public void delete(Integer idPhoneLine) throws PhoneLineNotExistsException {

        this.phoneLineRepository.desactive(idPhoneLine);

=======
>>>>>>> parent of 5e7514a... Merge branch 'UTN-Phones-B1'
    }

    public List<PhoneLine> getPhoneLines(){
        List<PhoneLine> phonelines = new ArrayList<PhoneLine>();

        phonelines = phoneLineRepository.findAll();

        return phonelines;
    }

    public PhoneLine getPhoneLineByNumber(String number){
        PhoneLine phoneline = new PhoneLine();

        phoneline = phoneLineRepository.findByPhoneNumber(number).get();

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

    public void addPhoneLine(PhoneLine phoneLine) {
        this.phoneLineRepository.save(phoneLine);
    }
}
