package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import com.utn.utnphones.models.User;
import com.utn.utnphones.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserById(Integer idUser){
        User user = new User();

        user =  this.userRepository.findById(idUser).get();

        return user;
    }

    /*@GetMapping("/-pass={idUser}")*/
    public String getPassById(Integer idUser)
    {
        String pass = new String();

        pass = this.userRepository.findPassById(idUser);

        return pass;
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }
}
