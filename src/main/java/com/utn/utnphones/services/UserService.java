package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.utn.utnphones.exceptions.UserNotFoundException;
import com.utn.utnphones.models.User;
import com.utn.utnphones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService (final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*@GetMapping("/")*/
    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();

        users = this.userRepository.findAll();

        return users;
    }

    /*@GetMapping("/{idUser}")*/
    public ResponseEntity<User> getUserById(Integer idUser) throws UserNotFoundException{
        User user = new User();

        HttpStatus userStatus = HttpStatus.OK;

        user = this.userRepository.getById(idUser);

        Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException(idUser));

        return new ResponseEntity<User>(user, userStatus);
    }

    /*@GetMapping("/-pass={idUser}")*/
    public String getPassById(Integer idUser)
    {
        String pass = new String();

        pass = this.userRepository.findPassById(idUser);

        return pass;
    }
}
