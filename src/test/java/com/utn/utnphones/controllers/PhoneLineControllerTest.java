package com.utn.utnphones.controllers;

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.GoneException;
import com.utn.utnphones.exceptions.PhoneLineNotExistsException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.services.PhoneLineService;
import com.utn.utnphones.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneLineControllerTest {
    @Mock
    PhoneLineService phoneLineServiceMockito;

    @Mock
    UserService userServiceMock;

    @InjectMocks
    PhoneLineController phoneLineController;

    @Before
    public void setUp(){initMocks(this);}

    private PhoneLineDto createPhoneLineDto(){
        PhoneLineDto dto = new PhoneLineDto();
        dto.setIdLineType(1);
        dto.setIdUser(1);
        dto.setLineStatus(LineStatus.ENABLED.toString());
        dto.setPhoneNumber("2234444");
        return dto;
    }
    private LineType createLineType(){
        return LineType.builder()
                .idLineType(1)
                .name("celular")
                .digitsQty(10)
                .build();
        }

    private PhoneLine createPhoneLine(){
            return PhoneLine.builder()
                    .idPhoneLine(1)
                    .phoneNumber("223455")
                    .user(new User())
                    .lineType(createLineType())
                    .locality(new Locality())
                    .active(true)
                    .build();
    }
    /* No puedo testear metodo getLocation
    @Test
    public void addOkTest() throws ValidationException, UserException {
        PhoneLineDto dto = createPhoneLineDto();
        PhoneLine phoneLine = createPhoneLine();
        when(this.phoneLineServiceMockito.add(dto)).thenReturn(phoneLine);
        ResponseEntity<PhoneLine> response = this.phoneLineController.add(dto);
        Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
    */

    @Test
    public void changeStatusDisabled() throws GoneException, PhoneLineNotExistsException, ValidationException {
        String phoneNumber = "22333232";
        String status = "disable";
        PhoneLine phoneLine = createPhoneLine();
        phoneLine.setStatus(LineStatus.ENABLED);
        when(this.phoneLineServiceMockito.getByPhoneNumber(phoneNumber)).thenReturn(phoneLine);
        when(this.phoneLineServiceMockito.update(phoneLine)).thenReturn(phoneLine);
        ResponseEntity<PhoneLine> responseEntity = this.phoneLineController.changeStatus(phoneNumber,status);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void changeStatusEnableTest() throws GoneException, PhoneLineNotExistsException, ValidationException {
        String phoneNumber = "22333232";
        String status = "enable";
        PhoneLine phoneLine = createPhoneLine();
        phoneLine.setStatus(LineStatus.ENABLED);
        when(this.phoneLineServiceMockito.getByPhoneNumber(phoneNumber)).thenReturn(phoneLine);
        when(this.phoneLineServiceMockito.update(phoneLine)).thenReturn(phoneLine);
        ResponseEntity<PhoneLine> responseEntity = this.phoneLineController.changeStatus(phoneNumber,status);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void changeStatusSuspendTest() throws GoneException, PhoneLineNotExistsException, ValidationException {
        String phoneNumber = "22333232";
        String status = "suspend";
        PhoneLine phoneLine = createPhoneLine();
        phoneLine.setStatus(LineStatus.ENABLED);
        when(this.phoneLineServiceMockito.getByPhoneNumber(phoneNumber)).thenReturn(phoneLine);
        when(this.phoneLineServiceMockito.update(phoneLine)).thenReturn(phoneLine);
        ResponseEntity<PhoneLine> responseEntity = this.phoneLineController.changeStatus(phoneNumber,status);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test(expected = PhoneLineNotExistsException.class )
    public void changeStatusNullTest() throws GoneException, PhoneLineNotExistsException, ValidationException {
        String phoneNumber = "22333232";
        String status = "suspend";
        PhoneLine phoneLine = createPhoneLine();
        phoneLine.setStatus(LineStatus.ENABLED);
        when(this.phoneLineServiceMockito.getByPhoneNumber(phoneNumber)).thenReturn(null);
        this.phoneLineController.changeStatus(phoneNumber,status);

    }

    @Test(expected = ValidationException.class )
    public void changeStatusNotValidTest() throws GoneException, PhoneLineNotExistsException, ValidationException {
        String phoneNumber = "22333232";
        String status = "cualca";
        PhoneLine phoneLine = createPhoneLine();
        phoneLine.setStatus(LineStatus.ENABLED);
        when(this.phoneLineServiceMockito.getByPhoneNumber(phoneNumber)).thenReturn(phoneLine);
        this.phoneLineController.changeStatus(phoneNumber,status);

    }

    @Test
    public void deleteOkTest() throws GoneException, PhoneLineNotExistsException {
    Integer id = 1;
    PhoneLine phoneline = createPhoneLine();
    when(this.phoneLineServiceMockito.getById(id)).thenReturn(phoneline);
        doNothing().when(this.phoneLineServiceMockito).delete(id);
        ResponseEntity response = this.phoneLineController.delete(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test(expected = PhoneLineNotExistsException.class)
    public void deleteNotExistTest() throws GoneException, PhoneLineNotExistsException {
        Integer id = 1;
        PhoneLine phoneline = createPhoneLine();
        when(this.phoneLineServiceMockito.getById(id)).thenThrow(new PhoneLineNotExistsException("No existe"));
        this.phoneLineController.delete(id);
    }

    @Test(expected = GoneException.class)
    public void deleteGoneTest() throws GoneException, PhoneLineNotExistsException {
        Integer id = 1;
        PhoneLine phoneline = createPhoneLine();
        when(this.phoneLineServiceMockito.getById(id)).thenThrow(new GoneException("Ya fue eliminado"));
        this.phoneLineController.delete(id);
    }

    @Test
    public void getPhoneLineByNumberTest() throws GoneException, PhoneLineNotExistsException {
        String number = "22332332";
        PhoneLine phoneLine = createPhoneLine();
        when(this.phoneLineServiceMockito.getPhoneLineByNumber(number)).thenReturn(phoneLine);
        ResponseEntity<PhoneLine> response = this.phoneLineController.getPhoneLineByNumber(number);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getPhoneLinesTest(){
        List<PhoneLine> list = new ArrayList<>();
        PhoneLine phoneLine = createPhoneLine();
        list.add(phoneLine);
        when(this.phoneLineServiceMockito.getPhoneLines()).thenReturn(list);
        ResponseEntity<List<PhoneLine>> response = this.phoneLineController.getPhoneLines();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
