package com.utn.utnphones.repositories;


import com.utn.utnphones.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u.* from users u where u.id_user = ?1", nativeQuery = true)
    User getById(Integer idUser);

    @Query(value = "select u.* from users u where u.user_name = ?1 and u.password = ?2", nativeQuery = true)
    User getByUsername(String username, String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update users u set u.active = false where u.id_user = ?1", nativeQuery = true)
    void delete(Integer idUser);
}
