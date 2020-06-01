package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {
    @Query(value = "select c.* from calls c where c.id_tariff = ?1 order by c.id_call arc", nativeQuery = true)
    List<Call> findByTariff(Integer idTariff);

    @Query(value = "select c.* from calls c where c.id_bill = ?1 order by c.id_call arc", nativeQuery = true)
    List<Call> findByBill(Integer idBill);

    @Query(value = "select c.* from calls c where c.id_phone_line_from = ?1 order by c.id_call arc", nativeQuery = true)
    List<Call> findByLineFrom(String phoneLineFrom);

    @Query(value = "select c.* from calls c where c.id_phone_line_to = ?1 order by c.id_call arc", nativeQuery = true)
    List<Call> findByLineTo(String phoneLineTo);

    @Query(value = "select c.* from calls c where convert(DATE, c.date) = ?1 order by c.id_bill asc", nativeQuery = true)
    List<Call> findByDate(String date);

    @Query(value = "select c.* from calls c " +
            "where convert(DATE, c.date) > ?1 and convert(DATE, c.date) > ?2 " +
            "order by c.id_bill asc", nativeQuery = true)
    List<Call> findCallsBtwDates(String date1, String date2);

}