package com.utn.utnphones.services;

import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.LocalityRepository;
import com.utn.utnphones.repositories.UserRepository;
import com.utn.utnphones.utils.ModelsTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;
<<<<<<< HEAD
=======

>>>>>>> parent of 616959d... Merge branch 'UTN-Phones-B1'
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LocalityRepository localityRepository;

    private UserService userService;
    private ModelsTestHelper helper;

    @Before
    public void setUp(){
        initMocks(this);
        this.userService = new UserService(userRepository,localityRepository);
    }

    @Test()
    public void LoginOk(){
        User user = this.helper.getUser();

        when(this.userRepository.getByUsername()).thenReturn(list);

        List<Client> listTest = this.clientService.getAll(10,0);

        Assert.assertEquals(list.size(), listTest.size());*/

    }
}
