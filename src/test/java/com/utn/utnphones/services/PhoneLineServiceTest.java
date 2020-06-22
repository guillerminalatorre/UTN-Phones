package com.utn.utnphones.services;

import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.repositories.LineTypeRepository;
import com.utn.utnphones.repositories.PhoneLineRepository;
import com.utn.utnphones.repositories.UserRepository;
import com.utn.utnphones.utils.ModelsTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    private ModelsTestHelper helper = new ModelsTestHelper();

    @Before
    public void setUp(){
        initMocks(this);
        this.phoneLineService = new PhoneLineService(phoneLineRepository, userRepository, lineTypeRepository);
    }

    @Test()
    public void addOk() throws ValidationException, UserException {
        PhoneLine phoneLine = new PhoneLine(303,"2317678906", this.helper.getUser(), this.helper.getLineType(),this.helper.getLocality(), LineStatus.ENABLED, true );

        when(this.phoneLineRepository.save(phoneLine)).thenReturn(phoneLine);

        PhoneLineDto phoneLineDto = new PhoneLineDto(1,"2317678906", 1, "ENABLED");

        PhoneLine phoneLine1 = this.phoneLineService.add(phoneLineDto);

        Assert.assertEquals(phoneLine, phoneLine1);

    }

}
