package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "select b.* from bills b " +
            "where b.datee BETWEEN concat(?1, ' 00:00:00') and DATE_ADD(concat( ?2, ' 00:00:00'), interval 1 DAY) " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsBtwDates(String startDate, String finalDate);

    @Query( value= "select b.* from bills b " +
            "inner join phone_lines pl on pl.id_phone_line = b.id_phone_line " +
            "where pl.id_user = ?1 order by b.datee asc", nativeQuery = true)
    List<Bill> getBillsByIdUser(Integer idUser);

    @Query(value = "select b.* from bills b " +
            "inner join phone_lines pl on pl.id_phone_line = b.id_phone_line " +
            "where pl.id_user = ?3 and " +
            "b.datee BETWEEN concat(?1, ' 00:00:00') and DATE_ADD(concat( ?2, ' 00:00:00'), interval 1 DAY) " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsBtwDatesByIdUser(String startDate, String finalDate, Integer isUser);

}
