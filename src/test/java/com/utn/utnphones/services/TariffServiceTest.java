package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.repositories.TariffRepository;
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

public class TariffServiceTest {
    @Mock
    TariffRepository tariffRepository;

    @InjectMocks
    TariffService tariffService;

    @Before
    public void setUp() {
        initMocks(this);
    }

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
    public void getTariffsOkTest(){
        List<Tariff> list = new ArrayList<>();
        Tariff tariff = createTariff();
        list.add(tariff);
        when(this.tariffRepository.findAll()).thenReturn(list);
        ResponseEntity<List<Tariff>> response = this.tariffService.getTariffs();
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getTariffsNoContentTest(){
        List<Tariff> list = new ArrayList<>();
        when(this.tariffRepository.findAll()).thenReturn(list);
        ResponseEntity<List<Tariff>> response = this.tariffService.getTariffs();
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void getTariffByLocalityFromToOkTest() throws TariffNotExistsException {
        Tariff tariff = createTariff();
        Integer idLocalityFrom = 1;
        Integer idLocalityTo = 1;
        when(this.tariffRepository.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo)).thenReturn(tariff);
        Assert.assertEquals(tariff,this.tariffService.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo));
    }

    @Test(expected = TariffNotExistsException.class)
    public void getTariffByLocalityFromToEmptyTariffTest() throws TariffNotExistsException {
        Integer idLocalityFrom = 1;
        Integer idLocalityTo = 1;
        when(this.tariffRepository.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo)).thenReturn(null);
        this.tariffService.getTariffByLocalityFromTo(idLocalityFrom,idLocalityTo);
    }



}
