package com.utn.utnphones.services;
import java.util.List;
import java.util.Optional;

import com.utn.utnphones.dto.UserDto;
import com.utn.utnphones.exceptions.UserAlreadyExistsException;
import com.utn.utnphones.exceptions.UserNotFoundException;
import com.utn.utnphones.exceptions.UserException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.User;
import com.utn.utnphones.models.enums.UserType;
import com.utn.utnphones.repositories.LocalityRepository;
import com.utn.utnphones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LocalityRepository localityRepository;

    @Autowired
    public UserService(final UserRepository userRepository, LocalityRepository localityRepository){
        this.userRepository = userRepository;
        this.localityRepository = localityRepository;
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Integer idUser) throws UserNotFoundException{
        return this.userRepository.findById(idUser).get();
    }

    public String getPassById(Integer idUser) {
        return this.userRepository.findPassById(idUser);
    }

    public User login(String username, String password) throws UserException {

         User user = userRepository.getByUsername(username, password);

         return Optional.ofNullable(user).orElseThrow(() -> new UserException("User not exists"));
    }

    public User createUser(UserDto user) throws UserAlreadyExistsException, ValidationException {

        Locality locality = localityRepository.getById(user.getIdLocality());

        if(locality == null){
            return (User) Optional.ofNullable(null).orElseThrow(() -> new ValidationException("Locality does not exists"));
        }

        User save = new User();

        save.setLocality(locality);
        save.setName(user.getName());
        save.setLastname(user.getLastname());
        save.setUserName(user.getUsername());
        save.setPassword(user.getPassword());
        save.setIdNumber(user.getIdNumber());
        save.setActive(user.getActive());

        return userRepository.save(save);
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
        old.setPassword(userDto.getPassword());
        old.setIdNumber(userDto.getIdNumber());
        old.setActive(userDto.getActive());

        if(userDto.getUserType().equals(UserType.BACKOFFICE) ){
            old.setUserType(UserType.BACKOFFICE);
        }
        else{
            old.setUserType(UserType.CLIENT);
        }

        return this.userRepository.save(old);
    }

}
