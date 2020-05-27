package com.utn.utnphones.repositories;

import java.util.List;
import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.LineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine, String> {
    @Query(value = "select p.id_user from phone_lines p where p.phone_number like ?1", nativeQuery = true)
    Integer findUserByNumber(String number);

    @Query(value = "select p.status from phone_lines p where p.phone_number like ?1", nativeQuery = true)
    LineStatus findStatusByNumber(String phone_number);

    @Query(value = "select p.* from phone_lines p where p.id_user = ?1", nativeQuery = true)
    List<PhoneLine> findByUser(Integer idUser);
}
