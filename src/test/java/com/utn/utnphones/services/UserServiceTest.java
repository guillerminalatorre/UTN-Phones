package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
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
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LocalityRepository localityRepository;

    private UserService userService;
    private ModelsTestHelper helper = new ModelsTestHelper();

    @Before
    public void setUp(){
        initMocks(this);
        this.userService = new UserService(userRepository,localityRepository);
    }

    @Test()
    public void loginOk() throws UserException {
        User user = this.helper.getUser();

        when(this.userRepository.getByUsername(user.getUserName(), user.getPassword())).thenReturn(user);

        User user1= this.userService.login(user.getUserName(), user.getPassword());

        Assert.assertEquals(user, user1);

    }

    @Test(expected = UserException.class)
    public void loginFail() throws UserException {
        this.userService.login("none", "none");

    }

}
