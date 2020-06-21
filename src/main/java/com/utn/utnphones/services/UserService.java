package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.utn.utnphones.dto.LoginRequestDto;
import com.utn.utnphones.dto.UserDto;
import com.utn.utnphones.exceptions.UserAlreadyExistsException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.exceptions.ValidationException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.LocalityRepository;
import com.utn.utnphones.repositories.UserRepository;
import com.utn.utnphones.utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final LocalityRepository localityRepository;

    @Autowired
    public UserService(final UserRepository userRepository, LocalityRepository localityRepository){
        this.userRepository = userRepository;
        this.localityRepository = localityRepository;
    }

    public User login(String username, String password) throws UserException {

        User user = userRepository.getByUsername(username, password);

        return Optional.ofNullable(user).orElseThrow(() -> new UserException("User not exists"));
    }

    public User add (UserDto user) throws UserAlreadyExistsException, ValidationException {

        if(!user.getUserType().equals(UserType.BACKOFFICE.toString())) {
            if (!user.getUserType().equals(UserType.CLIENT.toString())) {
                return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("User type is not valid"));
            }
        }

        Locality locality = localityRepository.getById(user.getIdLocality());

        if(locality == null){
            return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Locality does not exists"));
        }

        User save = new User();
        Hash hash = new Hash();

        save.setLocality(locality);
        save.setName(user.getName());
        save.setLastname(user.getLastname());
        save.setUserName(user.getUsername());
        save.setPassword(hash.getHash(user.getPassword()));
        save.setActive(user.getActive());

        if(user.getUserType().equals(UserType.BACKOFFICE) ){
            save.setUserType(UserType.BACKOFFICE);
        }
        else{
            save.setUserType(UserType.CLIENT);
        }

        return userRepository.save(save);
    }

    public ResponseEntity<List<User>> getUsersActive(){

        List<User> users = this.userRepository.findAll();

        List<User> reply = new ArrayList<User>();

        for (User u: users) {
            if(u.getActive() == true && u.getUserType() == UserType.CLIENT){
                reply.add(u);
            }
        }

        if(!reply.isEmpty()){
            return ResponseEntity.ok(reply);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    public User getUserById(Integer idUser) throws UserException {
        User user = this.userRepository.findById(idUser).get();

        return Optional.ofNullable(user).orElseThrow(() -> new UserException("User not exists"));
    }

    public void delete(Integer idUser) throws UserException {
        userRepository.delete(idUser);
    }

    //PERMITE HACER A UN CLIENTE PARTE DEL BACKOFFICE

    public User update(Integer idUser, UserDto userDto) throws ValidationException {
        User old = this.userRepository.findById(idUser).get();

        if(!userDto.getUserType().equals(UserType.BACKOFFICE.toString())){
            if(!userDto.getUserType().equals(UserType.CLIENT.toString())) {
                return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("User type is not valid"));
            }
        }

        Locality locality = localityRepository.getById(userDto.getIdLocality());

        if(locality == null){
            return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Locality does not exists"));
        }

        old.setLocality(locality);
        old.setName(userDto.getName());
        old.setLastname(userDto.getLastname());
        old.setUserName(userDto.getUsername());
        old.setActive(userDto.getActive());

        if(userDto.getUserType().equals(UserType.BACKOFFICE) ){
            old.setUserType(UserType.BACKOFFICE);
        }
        else{
            old.setUserType(UserType.CLIENT);
        }

        return this.userRepository.save(old);
    }

    //UPDATE POR PARTE DEL CLIENTE
    public User update(Integer idClient, LoginRequestDto user) throws ValidationException {
        User save = this.userRepository.getById(idClient);

        Hash hash = new Hash();

        save.setUserName(user.getUsername());
        save.setPassword(hash.getHash(user.getPassword()));

        return this.userRepository.save(save);
    }

}
