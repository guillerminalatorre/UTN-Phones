package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.services.TariffService;
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

public class TariffControllerTest {
    @Mock
    TariffService tariffServiceMockito;

    @InjectMocks
    TariffController tariffController;

    @Before
    public void setUp(){initMocks(this);}

    private Tariff createTariff(){
        return Tariff.builder()
                .idTariff(1)
                .localityFrom(new Locality())
                .localityTo(new Locality())
                .costPrice(150.1)
                .price(200.1)
                .build();
    }

    @Test
    public void getTariffOkTest(){
        List<Tariff> list = new ArrayList<>();
        list.add(createTariff());
        when(this.tariffServiceMockito.getTariffs()).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Tariff>> response = this.tariffController.getTariffs();
        Assert.assertEquals(list.size(),response.getBody().size());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getTariffByLocalityFromToOkTest() throws TariffNotExistsException {
        Integer idLocalityFrom = 1;
        Integer idLocalityTo = 2;
        Tariff tariff = createTariff();
        when(this.tariffServiceMockito.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo)).thenReturn(tariff);
        ResponseEntity<Tariff> response = this.tariffController.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
