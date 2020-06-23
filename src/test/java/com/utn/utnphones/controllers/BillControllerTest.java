package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.services.BillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BillControllerTest {
    @Mock
    BillService billServiceMockito;

    @InjectMocks
    BillController billController;

    @Before
    public void setUp(){initMocks(this);}

    @Test
    public void getBillsByIdUserTest() throws UserException {
        Integer id = 1;
        List<Bill> list = new ArrayList<>();
        list.add(new Bill());
        when(this.billServiceMockito.getBillsByIdUser(id)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Bill>> response = this.billController.getBillsByIdUser(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsBtwDatesByIdUserTest() throws UserException {
        Integer idUser = 1;
        String startDate = "ahora";
        String finalDate = "final";
        List<Bill> list = new ArrayList<>();
        list.add(new Bill());
        when(this.billServiceMockito.getBillsBtwDatesByIdUser(startDate,finalDate,idUser)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Bill>> response = this.billController.getBillsBtwDatesByIdUser(startDate,finalDate,idUser);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsBtwDatesTest(){
        String startDate = "ahora";
        String finalDate = "final";
        List<Bill> list = new ArrayList<>();
        list.add(new Bill());
        when(this.billServiceMockito.getBillsBtwDates(startDate,finalDate)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Bill>> response = this.billController.getBillsBtwDates(startDate,finalDate);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
