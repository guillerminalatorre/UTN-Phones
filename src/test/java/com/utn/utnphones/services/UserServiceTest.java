package com.utn.utnphones.services;

import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.dto.NewUserDto;
import com.utn.utnphones.dto.UpdateUserDto;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Province;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.LocalityRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LocalityRepository localityRepository;

    private UserService userService;

    @Before
    public void setUp(){
        initMocks(this);
        this.userService = new UserService(userRepository,localityRepository);
    }

    private UpdateUserDto createUpdateUserDto()
    {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setUserType(UserType.CLIENT.toString());
        dto.setName("Mauro");
        dto.setLastname("Menendez");
        dto.setIdLocality(1);
        return dto;
    }

    private NewUserDto createNewUserDto(Integer idLocality, String name, String lastname, String username, String idNumber,String userType, Boolean active){
        NewUserDto dto = new NewUserDto();
        dto.setIdLocality(idLocality);
        dto.setName(name);
        dto.setLastname(lastname);
        dto.setUsername(username);
        dto.setIdNumber(idNumber);
        dto.setUserType(userType);
        dto.setActive(active);
        return dto;
    }

    private Locality createLocality(List<Tariff> list,List<Call> calss){
       return Locality.builder()
                .idLocality(1)
                .province(new Province())
                .name("localidad")
                .prefix("prefijo")
                .tarrifsFrom(list)
                .tarrifsTo(list)
                .callsFrom(calss)
                .callsTo(calss)
                .build();
    }
    private User createUser(){
        return User.builder()
                .idNumber("2236211294")
                .password("123")
                .locality(new Locality())
                .name("Mauro")
                .lastname("Cucamonga")
                .userType(UserType.CLIENT)
                .userName("Cuca")
                .idUser(1)
                .active(Boolean.TRUE)
                .build();
    }

    @Test()
    public void loginOk() throws UserException {
        User user = this.createUser();

        when(this.userRepository.getByUsername(user.getUserName(), user.getPassword())).thenReturn(user);

        User user1= this.userService.login(user.getUserName(), user.getPassword());

        Assert.assertEquals(user, user1);

    }

    @Test(expected = UserException.class)
    public void loginFail() throws UserException {
        this.userService.login("none", "none");

    }

    @Test
    public void addClientTest() throws ValidationException {
        Integer id = 1;
        NewUserDto dto = createNewUserDto(1, "Mauro", "CR", "Mazorca", "223blabla",UserType.CLIENT.toString(), Boolean.TRUE);
        User user = createUser();
        List<Tariff> listTariff = new ArrayList<>();
        List<Call> listCall = new ArrayList<>();
        Locality locality = createLocality(listTariff,listCall);
        when(this.localityRepository.getById(id)).thenReturn(locality);
        when(this.userRepository.save(user)).thenReturn(user);
        User test = new User();
        test.setUserType(UserType.CLIENT);
        test.setLocality(locality);
        Assert.assertEquals(locality.getName(),test.getLocality().getName());
        Assert.assertEquals(UserType.CLIENT,test.getUserType());

    }

    @Test
    public void addBackOfficeTest() throws ValidationException {
        Integer id = 1;
        NewUserDto dto = createNewUserDto(1, "Mauro", "CR", "Mazorca", "223blabla",UserType.BACKOFFICE.toString(), Boolean.TRUE);
        User user = createUser();
        List<Tariff> listTariff = new ArrayList<>();
        List<Call> listCall = new ArrayList<>();
        Locality locality = createLocality(listTariff,listCall);
        when(this.localityRepository.getById(id)).thenReturn(locality);
        when(this.userRepository.save(user)).thenReturn(user);
        User test = new User();
        test.setUserType(UserType.BACKOFFICE);
        test.setLocality(locality);
        Assert.assertEquals(locality,test.getLocality());
        Assert.assertEquals(UserType.BACKOFFICE,test.getUserType());

    }

    @Test(expected = ValidationException.class)
    public void addLocalityNullTest() throws ValidationException {
        Integer id = 1;
        NewUserDto dto = createNewUserDto(1, "Mauro", "CR", "Mazorca", "223blabla",UserType.CLIENT.toString(), Boolean.TRUE);
        when(this.localityRepository.getById(id)).thenReturn(null);
        User test = this.userService.add(dto);

    }

    @Test(expected = ValidationException.class)
    public void addUserTypeNullTest() throws ValidationException {
        Integer id = 1;
        NewUserDto dto = createNewUserDto(1, "Mauro", "CR", "Mazorca", "223blabla","josue", Boolean.TRUE);
        this.userService.add(dto);

    }

    @Test
    public void getUsersActiveOkTest(){
        List<User> listU = new ArrayList<>();
        User user = createUser();
        listU.add(user);
        when(this.userRepository.findAll()).thenReturn(listU);
        ResponseEntity<List<User>> response = this.userService.getUsersActive();
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getUsersActiveEmptyTest(){
        List<User> listU = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(listU);
        ResponseEntity<List<User>> response = this.userService.getUsersActive();
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void getUsersDisabledOkTest(){
        List<User> listU = new ArrayList<>();
        User user = createUser();
        user.setActive(false);
        listU.add(user);
        when(this.userRepository.findAll()).thenReturn(listU);
        ResponseEntity<List<User>> response = this.userService.getUsersActive();
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void getUsersDisabledEmptyTest(){
        List<User> listU = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(listU);
        ResponseEntity<List<User>> response = this.userService.getUsersActive();
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void getUserByIdOkTest() throws UserException {
        Integer id = 1;
        User user = createUser();
        when(this.userRepository.getById(id)).thenReturn(user);
        User test = this.userService.getUserById(id);
        Assert.assertEquals(user,test);

    }

    @Test(expected = UserException.class)
    public void getUserByIdNullTest() throws UserException {
        Integer id = 1;
        User user = createUser();
        when(this.userRepository.getById(id)).thenReturn(null);
        User test = this.userService.getUserById(id);

    }

    @Test
    public void deleteTest() throws UserException {
        Integer id = 1;
        doNothing().when(this.userRepository).delete(id);
        this.userService.delete(id);
    }

    @Test
    public void updateOkTest() throws ValidationException {
        Integer id = 1;
        User user = createUser();
        UpdateUserDto dto = createUpdateUserDto();
        List<Tariff> listTariff = new ArrayList<>();
        List<Call> listCall = new ArrayList<>();
        Locality locality = createLocality(listTariff,listCall);
        when(this.userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        when(this.localityRepository.getById(id)).thenReturn(locality);
        when(this.userRepository.save(user)).thenReturn(user);
        User test = this.userService.update(id,dto);
        Assert.assertEquals(locality.getName(),test.getLocality().getName());
        Assert.assertEquals(UserType.CLIENT,test.getUserType());
    }

    @Test(expected = ValidationException.class)
    public void updateUserTypeNotValidTest() throws ValidationException {
        Integer id = 1;
        User user = createUser();
        UpdateUserDto dto = createUpdateUserDto();
        dto.setUserType("cuca");
        List<Tariff> listTariff = new ArrayList<>();
        List<Call> listCall = new ArrayList<>();
        Locality locality = createLocality(listTariff,listCall);
        when(this.userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        User test = this.userService.update(id,dto);
    }


    @Test(expected = ValidationException.class)
    public void updateLocalityNullTest() throws ValidationException {
        Integer id = 1;
        User user = createUser();
        UpdateUserDto dto = createUpdateUserDto();
        List<Tariff> listTariff = new ArrayList<>();
        List<Call> listCall = new ArrayList<>();
        when(this.userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        when(this.localityRepository.getById(id)).thenReturn(null);
        when(this.userRepository.save(user)).thenReturn(user);
        User test = this.userService.update(id, dto);
    }



    @Test
    public void updateClientTest() throws ValidationException {
        Integer id = 1;
        User user = createUser();
        LoginRequestDto dto = new LoginRequestDto("name","cuca");
        when(this.userRepository.getById(id)).thenReturn(user);
        when(this.userRepository.save(user)).thenReturn(user);
        User test = this.userService.update(id,dto);
        Assert.assertEquals(user,test);
    }
}
