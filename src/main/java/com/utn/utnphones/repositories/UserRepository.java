package com.utn.utnphones.repositories;


import com.utn.utnphones.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u.password from users u where u.id_user = ?1", nativeQuery = true)
    String findPassById(Integer idUser);

    @Query(value = "select u.* from users u where u.id_user = ?1", nativeQuery = true)
    User getById(Integer idUser);
}
