package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.services.CallService;
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

public class CallControllerTest {
    @Mock
    CallService callServiceMockito;

    @InjectMocks
    CallController callController;

    @Before
    public void setUp(){initMocks(this);}

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
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.callServiceMockito.getUserCalls(id)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Call>> response = this.callController.getUserCalls(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getLocalitiesToByCallIdUserOkTest(){
        Integer id = 1;
        List<Locality> list = new ArrayList<>();
        list.add(new Locality());
        when(this.callServiceMockito.getLocalitiesToByCallIdUser(id)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Locality>> response = this.callController.getLocalitiesToByCallIdUser(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getCallsBtwDatesByUserTest() throws UserException {
        String startDate = "primera";
        String finalDate = "final";
        Integer idUser = 1;
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.callServiceMockito.getCallsBtwDatesByUser(idUser,startDate,finalDate)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Call>> response = this.callController.getCallsBtwDatesByUser(startDate,finalDate,idUser);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getCallsBtwDatesTest() throws UserException {
        String startDate = "primera";
        String finalDate = "final";
        Call call = createCall();
        List<Call> list = new ArrayList<>();
        list.add(call);
        when(this.callServiceMockito.getCallsBtwDates(startDate,finalDate)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Call>> response = this.callController.getCallsBtwDates(startDate,finalDate);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void addCallTest() throws ValidationException {
        Call call = createCall();
        when(this.callServiceMockito.addCall(call)).thenReturn(ResponseEntity.ok(call));
        ResponseEntity<Call> response = this.callController.addCall(call);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
