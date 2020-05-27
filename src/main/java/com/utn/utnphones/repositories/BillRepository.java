package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "select b.* from bills b where b.status = PAID order by b.id_bill asc", nativeQuery = true)
    List<Bill> findAllPaids();

    @Query(value = "select b.* from bills b where b.status = UNPAID order by b.id_bill asc", nativeQuery = true)
    List<Bill> findAllUnpaid();

    @Query(value = "select b.* from bills b where b.date = ?1 order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsFromDate(String date);

    @Query(value = "select b.* from bills b " +
            "where convert(DATE, b.date) > ?1 and convert(DATE, b.date) < ?2 " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsBtwDates(String startDate, String finalDate);

    @Query(value = "select b.* from bills b " +
            "where b.phoneline_phone_number = ?1 " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findByPhoneNumber(String phone_number);

    @Query( value= "select b.phoneline_phone_number from bills b" +
            "where b.idBill = ?1", nativeQuery = true)
    String findPhoneNumberById(Integer idBill);
}
