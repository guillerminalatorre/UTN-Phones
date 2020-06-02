package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Province;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.repositories.TariffRepository;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TariffServiceTest {

    private TariffService tariffService;

    @Mock
    TariffRepository tariffRepository;

    @Before
    public void SetUp(){
        initMocks(this);
        tariffService = new TariffService(tariffRepository);
    }

    @Test
    public void testGetTariffByIdOK() throws TariffNotExistsException{

        Province prov = new Province(1, "Buenos Aires");

        Locality fromTo = new Locality(1,prov,"12 de octubre", "2317",null,null);

        Tariff tariff = new Tariff(1,fromTo, fromTo, 2.3,3.45);

        when(tariffRepository.getById(1)).thenReturn(tariff);

        Tariff returnedTariff = tariffService.getTariffById(1);

        assertEquals(tariff.getIdTariff(), returnedTariff.getIdTariff());
        assertEquals(tariff.getLocalityFrom(), returnedTariff.getLocalityFrom());
        assertEquals(tariff.getLocalityTo(), returnedTariff.getLocalityTo());
        assertEquals(tariff.getCostPrice(), returnedTariff.getCostPrice());
        assertEquals(tariff.getPrice(), returnedTariff.getPrice());

        verify(tariffRepository, times(1)).getById(1);
    }


}
