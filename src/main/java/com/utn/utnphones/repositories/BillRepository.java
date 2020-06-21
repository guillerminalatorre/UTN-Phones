package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "select b.* from bills b " +
            "where convert(DATE, b.date) > ?1 and convert(DATE, b.date) < ?2 " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsBtwDates(String startDate, String finalDate);

    @Query( value= "select b.* from bills b " +
            "inner join phone_lines pl on pl.id_phone_line = b.id_phone_line " +
            "where pl.id_user = ?1 order by b.datee asc", nativeQuery = true)
    List<Bill> getBillsByIdUser(Integer idUser);

    @Query(value = "select b.* from bills b " +
            "inner join phone_lines pl on pl.id_phone_line = b.id_phone_line " +
            "where pl.id_user = ?3 and " +
            "b.datee BETWEEN DATE_ADD(concat(?1, ' 23:59:59.59'), INTERVAL -1 DAY) and concat( ?2, ' 23:59:59.59') " +
            "order by b.id_bill asc", nativeQuery = true)
    List<Bill> findBillsBtwDatesByIdUser(String startDate, String finalDate, Integer isUser);

}
