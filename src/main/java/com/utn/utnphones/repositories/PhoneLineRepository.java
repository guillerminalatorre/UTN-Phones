package com.utn.utnphones.repositories;

import java.util.List;
import java.util.Optional;

import com.utn.utnphones.models.PhoneLine;
import com.utn.utnphones.models.enums.LineStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine, String> {
    @Query(value = "select p.* from phone_lines p where p.phone_number = ?1", nativeQuery = true)
    PhoneLine getByPhoneNumber(String number);

    @Query(value = "select p.* from phone_lines p where p.id_phone_line like ?1", nativeQuery = true)
    PhoneLine getById(Integer idPhoneLine);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update phone_lines set active= false where id_phone_line = ?1", nativeQuery = true)
    void desactive(Integer phoneLine);

    PhoneLine save(PhoneLine phoneLine) throws DataIntegrityViolationException;

}
