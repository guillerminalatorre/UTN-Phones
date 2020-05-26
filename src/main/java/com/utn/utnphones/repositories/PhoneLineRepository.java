package com.utn.utnphones.repositories;

import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine, String> {
    @Query(value = "select p.user_id_user from phone_lines p where p.phone_number like ?1", nativeQuery = true)
    Integer findUserByNumber(String number);

    @Query(value = "select p.status phone_lines p where p.phone_number like ?1", nativeQuery = true)
    Status findStatusByNumber(String phone_number);
}
