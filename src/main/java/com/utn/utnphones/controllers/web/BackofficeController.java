package com.utn.utnphones.controllers.web;

import com.utn.utnphones.controllers.*;
import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.dto.UpdateUserDto;
import com.utn.utnphones.exceptions.*;
import com.utn.utnphones.dto.PhoneLineDto;
import com.utn.utnphones.dto.NewUserDto;
import com.utn.utnphones.models.*;
import com.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {

    private final UserController userController;
    private final PhoneLineController phoneLineController;
    private final TariffController tariffController;
    private final CallController callController;
    private final BillController billController;
    private final SessionManager sessionManager;

    @Autowired
    public BackofficeController(final UserController userController, final PhoneLineController phoneLineController, final SessionManager sessionManager, TariffController tariffController, CallController callController, BillController billController){
        this.userController = userController;
        this.phoneLineController = phoneLineController;
        this.sessionManager = sessionManager;
        this.tariffController = tariffController;
        this.callController = callController;
        this.billController = billController;
    }

    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }

    @PutMapping("/info")
    public ResponseEntity<User> update(@RequestHeader("Authorization") String sessionToken,
                                       @RequestBody LoginRequestDto user) throws ValidationException, UserException {
        User currentUser = getCurrentUser(sessionToken);

        return this.userController.update(currentUser.getIdUser(), user);
    }

    /**MANEJO DE CLIENTES*/

    @GetMapping("/users/active")
    public ResponseEntity<List<User>> getUsersActive(@RequestHeader("Authorization") String sessionToken) throws UserException {
        getCurrentUser(sessionToken);

        return this.userController.getUsersActive();
    }

    @GetMapping("/users/disabled")
    public ResponseEntity<List<User>> getUsersDisabled(@RequestHeader("Authorization") String sessionToken) throws UserException {
        getCurrentUser(sessionToken);

        return this.userController.getUsersDisabled();
    }
    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> getUserById (@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable(value = "idUser", required = true)Integer idUser) throws UserException {
        getCurrentUser(sessionToken);

        return this.userController.getUserById(idUser);
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestHeader("Authorization") String sessionToken, @RequestBody NewUserDto userDto) throws UserException, UserAlreadyExistsException, ValidationException {
        getCurrentUser(sessionToken);

        return this.userController.add(userDto);
    }

    @DeleteMapping("/users/{idUser}")
    public ResponseEntity deleteUser(@RequestHeader("Authorization") String sessionToken,
                                     @PathVariable(value = "idUser", required = true) Integer idUser) throws UserException {
        getCurrentUser(sessionToken);

        this.userController.delete(idUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{idUser}")
    public ResponseEntity<User> updateClient(@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable(value = "idUser", required = true) Integer idUser,
                                             @RequestBody UpdateUserDto updateUserDto) throws ValidationException, UserException {
        getCurrentUser(sessionToken);

        return this.userController.update(idUser, updateUserDto);
    }

    /**ALTA, BAJA Y SUSPENCION DE LINEAS*/

    @GetMapping("/phone-lines/{phoneNumber}")
    public ResponseEntity<PhoneLine> getPhoneLine(@RequestHeader("Authorization") String sessionToken,
                                                    @PathVariable(value = "phoneNumber", required = true) String phoneNumber) throws UserException, PhoneLineNotExistsException, GoneException {
        getCurrentUser(sessionToken);

        return this.phoneLineController.getPhoneLineByNumber(phoneNumber);
    }

    @GetMapping("/phone-lines")
    public ResponseEntity<List<PhoneLine>> getPhoneLines(@RequestHeader("Authorization") String sessionToken) throws UserException, PhoneLineNotExistsException, GoneException {
        getCurrentUser(sessionToken);

        return this.phoneLineController.getPhoneLines();
    }

    @PostMapping("/phone-lines")
    public ResponseEntity addPhoneLine(@RequestHeader("Authorization") String sessionToken,
                                       @RequestBody PhoneLineDto phoneLine) throws UserException, UserAlreadyExistsException, ValidationException, com.utn.utnphones.exceptions.ValidationException {
        getCurrentUser(sessionToken);

        return this.phoneLineController.add(phoneLine);
    }

    @PutMapping("/phone-lines/{phoneNumber}/status={status}")
    public ResponseEntity<PhoneLine> disablePhoneLine (@RequestHeader("Authorization") String sessionToken,
                                                      @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
                                                       @PathVariable(value = "Status", required = true) String status) throws ValidationException, UserException, PhoneLineNotExistsException, GoneException {
        getCurrentUser(sessionToken);

        return this.phoneLineController.changeStatus(phoneNumber, status);
    }

    @DeleteMapping("/phone-lines/{idPhoneLine}")
    public ResponseEntity<PhoneLine> deletePhoneLine (@RequestHeader("Authorization") String sessionToken,
                                                      @PathVariable(value = "idPhoneLine", required = true) Integer idPhoneLine) throws ValidationException, UserException, PhoneLineNotExistsException, GoneException {
        getCurrentUser(sessionToken);

        return this.phoneLineController.delete(idPhoneLine);
    }

    /**CONSULTA DE TARIFAS*/

    @GetMapping("/tariffs")
    public ResponseEntity<List<Tariff>> getTariffs(@RequestHeader("Authorization") String sessionToken) throws UserException {
        getCurrentUser(sessionToken);

        return this.tariffController.getTariffs();
    }

    @GetMapping("/tariffs/from={idLocalityFrom}/to={idLocalityTo}")
    public ResponseEntity<Tariff> getTariff(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable(value = "idLocalityFrom", required = true) Integer idLocalityFrom,
                                                  @PathVariable(value = "idLocalityTo", required = true) Integer idLocalityTo) throws UserException, TariffNotExistsException {
        getCurrentUser(sessionToken);

        return this.tariffController.getTariffByLocalityFromTo(idLocalityFrom, idLocalityTo);
    }

    /**CONSULTA DE LLAMADAS POR USUARIO*/

    @GetMapping("/calls/user/{idUser}")
        public ResponseEntity<List<Call>> getCallsByUser(@RequestHeader("Authorization") String sessionToken,
                                                         @PathVariable(value = "idUser", required = true) Integer idUser) throws UserException {
        getCurrentUser(sessionToken);

        return this.callController.getUserCalls(idUser);
    }

    @GetMapping( "/calls/user/{idUser}/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Call>> getCallsBtwDatesByUser(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                       @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate,
                                                       @PathVariable(value = "idUser", required = true) Integer idUser)
            throws UserException {

        getCurrentUser(sessionToken);

        return this.callController.getCallsBtwDatesByUser(startDate, finalDate, idUser);

    }

    @GetMapping( "/calls/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Call>> getCallsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                       @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserException {

        getCurrentUser(sessionToken);

        return this.callController.getCallsBtwDates(startDate, finalDate);

    }

    /**CONSULTA DE FACTURAS*/

    @GetMapping("/bills/user/{idUser}")
    public ResponseEntity<List<Bill>> getBillsByUser(@RequestHeader("Authorization") String sessionToken,
                                               @PathVariable(value = "idUser", required = true) Integer idUser) throws UserException {

        getCurrentUser(sessionToken);

        return this.billController.getBillsByIdUser(idUser);

    }

    @GetMapping("/bills/user/{idUser}/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Bill>> getBillsBtwDatesByUser(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable(value = "startDate", required = true) String startDate,
                                                       @PathVariable(value = "finalDate", required = true) String finalDate,
                                                       @PathVariable(value = "idUser", required = true) Integer idUser) throws UserException {
        getCurrentUser(sessionToken);

        return this.billController.getBillsBtwDatesByIdUser( startDate, finalDate, idUser);

    }

    @GetMapping("/bills/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<Bill>> getBillsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                             @PathVariable(value = "startDate", required = true) String startDate,
                                                             @PathVariable(value = "finalDate", required = true) String finalDate) throws UserException {
        getCurrentUser(sessionToken);

        return this.billController.getBillsBtwDates( startDate, finalDate);

    }

    private User getCurrentUser(String sessionToken) throws UserException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(() -> new UserException("User not logged"));
    }


}
