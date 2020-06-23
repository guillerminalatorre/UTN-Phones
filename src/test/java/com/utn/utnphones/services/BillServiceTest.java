package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.BillRepository;
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

import static com.utn.utnphones.models.enums.BillStatus.UNPAID;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BillServiceTest {
    @Mock
    BillRepository billRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BillService billService;

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

    private Bill createBill() {
        return Bill.builder()
                .idBill(1)
                .phoneLine(new PhoneLine())
                .callsQty(1)
                .costPrice((float) 2.3)
                .totalPrice((float) 5)
                .date(new Date())
                .dueDate(new Date())
                .status(UNPAID)
                .build();
    }

    @Test
    public void getBillsByUserOkTest() throws UserException {
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        Bill bill = createBill();
        list.add(bill);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.billRepository.getBillsByIdUser(id)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsByIdUser(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsByUserEmptyBillTest()throws UserException{
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.billRepository.getBillsByIdUser(id)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsByIdUser(id);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test(expected = UserException.class)
    public void getBillsByUserNullTest()throws UserException{
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(null);
        this.billService.getBillsByIdUser(id);
    }

    @Test
    public void getBillsBtwDatesByIdUserOkTest() throws UserException {
        String startDate = "empieza";
        String finalDate = "final";
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        Bill bill = createBill();
        list.add(bill);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.billRepository.findBillsBtwDatesByIdUser(startDate,finalDate,id)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsBtwDatesByIdUser(startDate,finalDate,id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsBtwDatesByIdUserEmptyBillTest() throws UserException{
        String startDate = "empieza";
        String finalDate = "final";
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.billRepository.findBillsBtwDatesByIdUser(startDate,finalDate,id)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsBtwDatesByIdUser(startDate,finalDate,id);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test(expected = UserException.class)
    public void getBillsBtwDatesByIdUserNullTest() throws UserException{
        String startDate = "empieza";
        String finalDate = "final";
        Integer id = 1;
        User u = createUser();
        List<Bill> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(null);
        this.billService.getBillsBtwDatesByIdUser(startDate,finalDate,id);
    }

    @Test
    public void getBillsBtwDatesOkTest(){
        String startDate = "empieza";
        String finalDate = "final";
        List<Bill> list = new ArrayList<>();
        Bill bill = createBill();
        list.add(bill);
        when(this.billRepository.findBillsBtwDates(startDate,finalDate)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsBtwDates(startDate,finalDate);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsBtwDates(){
        String startDate = "empieza";
        String finalDate = "final";
        List<Bill> list = new ArrayList<>();
        when(this.billRepository.findBillsBtwDates(startDate,finalDate)).thenReturn(list);
        ResponseEntity<List<Bill>> response = this.billService.getBillsBtwDates(startDate,finalDate);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }


}
