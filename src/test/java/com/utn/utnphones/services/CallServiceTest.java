package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.*;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.CallRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallServiceTest {
    @Mock
    CallRepository callRepositoryMockito;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CallService callService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    private User createUser() {
        return User.builder()
                .idNumber("2236211294")
                .password("123")
                .locality(new Locality())
                .name("Mauro")
                .lastname("Cucamonga")
                .userType(UserType.CLIENT)
                .userName("Cuca")
                .idUser(1)
                .build();
    }

    private Call createCall() {
        return Call.builder()
                .idCall(1)
                .tariff(new Tariff())
                .bill(new Bill())
                .phoneNumberFrom("223")
                .phoneNumberTo("22223")
                .localityFrom(new Locality())
                .localityTo(new Locality())
                .datee(new Date())
                .duration(new Date())
                .costPrice((float) 2.2)
                .totalPrice((float) 5.4)
                .build();
    }

    @Test
    public void getUserCallsOkTest() throws UserException {
        Integer id = 1;
        User u = createUser();
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.callRepositoryMockito.getCallsFromByUser(id)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getUserCalls(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getUserCallsNoContentTest() throws UserException {
        Integer id = 1;
        User u = createUser();
        List<Call> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.callRepositoryMockito.getCallsFromByUser(id)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getUserCalls(id);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test(expected = UserException.class)
    public void getUserCallsNullTest() throws UserException {
        Integer id = 1;
        User u = createUser();
        List<Call> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(null);
        ResponseEntity<List<Call>> response = this.callService.getUserCalls(id);
    }

    @Test
    public void getLocalitiesToByCallIdUserOkTest(){
        Integer id = 1;
        Locality locality = new Locality();
        List<Locality> list = new ArrayList<>();
        list.add(locality);
        when(this.callRepositoryMockito.getLocalitiesToByCallIdUser(id)).thenReturn(list);
        ResponseEntity<List<Locality>> response = this.callService.getLocalitiesToByCallIdUser(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getLocalitiesToByCallIdUserNoContentTest(){
        Integer id = 1;
        List<Locality> list = new ArrayList<>();
        when(this.callRepositoryMockito.getLocalitiesToByCallIdUser(id)).thenReturn(list);
        ResponseEntity<List<Locality>> response = this.callService.getLocalitiesToByCallIdUser(id);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void getCallsBtwDatesByUserOkTest() throws UserException {
        String startDate = "empieza";
        String finalDate = "termina";
        Integer id = 1;
        User u = createUser();
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.callRepositoryMockito.getCallsBtwDatesByUser(id, startDate, finalDate)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getCallsBtwDatesByUser(id, startDate, finalDate);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getCallsBtwDatesByUserNoContentTest() throws UserException {
        String startDate = "empieza";
        String finalDate = "termina";
        Integer id = 1;
        User u = createUser();
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.callRepositoryMockito.getCallsBtwDatesByUser(id, startDate, finalDate)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getCallsBtwDatesByUser(id, startDate, finalDate);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test(expected = UserException.class)
    public void getCallsBtwDatesByUserNullTest() throws UserException {
        String startDate = "empieza";
        String finalDate = "termina";
        Integer id = 1;
        Call call = createCall();
        when(this.userRepository.getById(id)).thenReturn(null);
        this.callService.getCallsBtwDatesByUser(id, startDate, finalDate);
    }

    @Test
    public void getCallsBtwDatesOkTest(){
        String startDate = "empieza";
        String finalDate = "termina";
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.callRepositoryMockito.getCallsBtwDates(startDate, finalDate)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getCallsBtwDates(startDate, finalDate);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getCallsBtwDatesNoContentTest(){
        String startDate = "empieza";
        String finalDate = "termina";
        List<Call> list = new ArrayList<>();
        when(this.callRepositoryMockito.getCallsBtwDates(startDate, finalDate)).thenReturn(list);
        ResponseEntity<List<Call>> response = this.callService.getCallsBtwDates(startDate, finalDate);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void addCallOkTest() throws ValidationException {
        Call call = createCall();
        when(this.callRepositoryMockito.save(call)).thenReturn(call);
        ResponseEntity<Call> response = this.callService.addCall(call);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
