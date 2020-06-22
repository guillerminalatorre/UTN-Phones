package com.utn.utnphones.services;

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.GoneException;
import com.utn.utnphones.exceptions.PhoneLineNotExistsException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.LineTypeRepository;
import com.utn.utnphones.repositories.PhoneLineRepository;
import com.utn.utnphones.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneLineServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneLineRepository phoneLineRepository;

    @Mock
    private LineTypeRepository lineTypeRepository;

    private PhoneLineService phoneLineService;

    @Before
    public void setUp(){
        initMocks(this);
        this.phoneLineService = new PhoneLineService(phoneLineRepository, userRepository, lineTypeRepository);
    }

    @Test()//PROBLEMAS TESTEANDO LINESTATUS ENUM
    public void addOk() throws ValidationException, UserException {
        Locality local = new Locality();

        /*LineStatus C = mock(LineStatus.class);
        when(C.ENABLED).thenReturn(LineStatus.ENABLED);

        PowerMockito.mockStatic(LineStatus.class);
        PowerMockito.when(LineStatus.ENABLED).thenReturn(LineStatus.ENABLED);*/


        User user = User.builder()
                .name("aa")
                .active(true)
                .password("aa")
                .lastname("aa")
                .userName("aa")
                .userType(UserType.BACKOFFICE)
                .idUser(1)
                .idNumber("11")
                .locality(local)
                .build();

        LineType line = LineType.builder()
                .idLineType(1)
                .name("aa")
                .digitsQty(10).build();

        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.TRUE)
                .user(user)
                .build();

        when(this.lineTypeRepository.getById(1)).thenReturn(line);

        when(this.userRepository.getById(1)).thenReturn(user);

        when(this.phoneLineRepository.save(phoneLine)).thenReturn(phoneLine);

        PhoneLineDto phoneLineDto = new PhoneLineDto();
        phoneLineDto.setIdUser(1);
        phoneLineDto.setLineStatus("ENABLED");
        phoneLineDto.setPhoneNumber("223");
        phoneLineDto.setIdLineType(1);

        PhoneLine phoneLine1 = this.phoneLineService.add(phoneLineDto);

        Assert.assertEquals(phoneLine, phoneLine1);

    }

    @Test()
    public void updateOk(){
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.FALSE)
                .build();

        when(this.phoneLineRepository.save(phoneLine)).thenReturn(phoneLine);

        PhoneLine phoneLine1 = this.phoneLineService.update(phoneLine);

        Assert.assertEquals(phoneLine, phoneLine1);
    }

    @Test()
    public void deleteOk(){
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.FALSE)
                .build();

        Integer idPhoneLine = 1;

        when(this.phoneLineRepository.desactive(idPhoneLine)).thenReturn(idPhoneLine);

        Integer reply = this.phoneLineService.delete(idPhoneLine);

        Assert.assertEquals(reply, idPhoneLine);
    }

    @Test()
    public void getPhoneLinesOk(){
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.TRUE)
                .build();

        List<PhoneLine> phoneLineList = new ArrayList<PhoneLine>();

        phoneLineList.add(phoneLine);

        when(this.phoneLineRepository.findAll()).thenReturn(phoneLineList);

        List<PhoneLine> phoneLineList1 = this.phoneLineService.getPhoneLines();

        Assert.assertEquals(phoneLineList.size() , phoneLineList1.size());
    }

    @Test()
    public void getByIdOk() throws GoneException, PhoneLineNotExistsException {
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.TRUE)
                .build();

        when(this.phoneLineRepository.getById(phoneLine.getIdPhoneLine())).thenReturn(phoneLine);

        PhoneLine phoneLine1 = this.phoneLineService.getById(phoneLine.getIdPhoneLine());

        Assert.assertEquals(phoneLine, phoneLine1);
    }


    @Test(expected = GoneException.class)
    public void getByIdFails1() throws GoneException, PhoneLineNotExistsException {
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phone = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.FALSE)
                .build();
        when(this.phoneLineRepository.getById(1)).thenReturn(phone);
        this.phoneLineService.getById(1);
    }


    @Test(expected = PhoneLineNotExistsException.class)
    public void getByIdFails2() throws GoneException, PhoneLineNotExistsException {
        this.phoneLineService.getById(309);
    }

    @Test()
    public void getByPhoneNumberOk() throws GoneException, PhoneLineNotExistsException {
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phoneLine = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.TRUE)
                .build();

        when(this.phoneLineRepository.getByPhoneNumber(phoneLine.getPhoneNumber())).thenReturn(phoneLine);

        PhoneLine phoneLine1 = this.phoneLineService.getByPhoneNumber(phoneLine.getPhoneNumber());

        Assert.assertEquals(phoneLine, phoneLine1);
    }


    @Test(expected = GoneException.class)
    public void getByPhoneNumberFails1() throws GoneException, PhoneLineNotExistsException {
        Locality local = new Locality();
        LineType line = new LineType();
        PhoneLine phone = PhoneLine.builder()
                .idPhoneLine(1)
                .phoneNumber("223")
                .locality(local)
                .lineType(line)
                .status(LineStatus.ENABLED)
                .active(Boolean.FALSE)
                .build();
        when(this.phoneLineRepository.getByPhoneNumber("223")).thenReturn(phone);
        this.phoneLineService.getByPhoneNumber("223");
    }


    @Test(expected = PhoneLineNotExistsException.class)
    public void getByPhoneNumberFails2() throws GoneException, PhoneLineNotExistsException {
        this.phoneLineService.getByPhoneNumber("22");
    }
}
