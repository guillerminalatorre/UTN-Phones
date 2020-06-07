package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.CallByLocalityToNotFound;
import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Province;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.repositories.CallRepository;
import com.utn.utnphones.repositories.TariffRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallServiceTest {

    private CallService callService;

    @Mock
    CallRepository callRepository;

    @Before
    public void SetUp(){
        initMocks(this);
        callService = new CallService(callRepository);
    }

    @Test
    public void testGetCallsByLineToOK() throws CallByLocalityToNotFound {

        Province prov = new Province(1, "Buenos aires", null);//3 idLty

        Province prov2 = new Province(20, "Santa fe", null); //269 idlty

        Locality ltyfrom = new Locality(3,prov,"25 de mayo (prov. Buenos aires)", "2345",null,null,null,null);

        Locality ltyto = new Locality(269,prov,"Campo piaggio", "3404",null,null,null,null);

        /*Call call = new Call(9, "2345889625", "3404596730", ltyfrom, ltyto, "2017-06-06 03:57:37", "00:16:52", 29.73, 44.6);

        List<Call> calls = new ArrayList<Call>();

        calls.add(call);

        when(callRepository.findByLineTo("3404596730")).thenReturn(calls);

        Tariff returnedTariff = tariffService.getTariffById(1);

        assertEquals(tariff.getIdTariff(), returnedTariff.getIdTariff());
        assertEquals(tariff.getLocalityFrom(), returnedTariff.getLocalityFrom());
        assertEquals(tariff.getLocalityTo(), returnedTariff.getLocalityTo());
        assertEquals(tariff.getCostPrice(), returnedTariff.getCostPrice());
        assertEquals(tariff.getPrice(), returnedTariff.getPrice());

        verify(tariffRepository, times(1)).getById(1);*/
    }
}
