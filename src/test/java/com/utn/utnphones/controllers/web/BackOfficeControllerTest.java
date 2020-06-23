package com.utn.utnphones.controllers.web;

import com.utn.utnphones.controllers.BillController;
import com.utn.utnphones.controllers.CallController;
import com.utn.utnphones.controllers.PhoneLineController;
import com.utn.utnphones.controllers.TariffController;
import com.utn.utnphones.controllers.UserController;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BackOfficeControllerTest {

    @Mock
    UserController userController;

    @Mock
    PhoneLineController phoneLineController;

    @Mock
    TariffController tariffController;

    @Mock
    CallController callController;

    @Mock
    BillController billController;

    @Mock
    SessionManager sessionManager;

    @InjectMocks
    BackofficeController backofficeController;

    @Before
    public void setUp(){initMocks(this);}

   /* No pude lograr testear metodos privados
    @Test
    public void getInfoTest(){
        User user = new User();
        spy(this.backofficeController);
        when(this.backofficeController,"getCurrentUser").thenReturn(user);
    }

    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }*/


}
