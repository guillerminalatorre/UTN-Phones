package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Call;
import com.utn.utnphones.models.Locality;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {

    @Query(value = "select concat(c.datee) as date, c.* from calls c " +
            "where c.datee BETWEEN DATE_ADD(concat(?1, ' 23:59:59.59'), INTERVAL -1 DAY) and concat( ?2, ' 23:59:59.59') " +
            "order by c.id_bill asc", nativeQuery = true)
    List<Call> getCallsBtwDates(String date1, String date2);

    @Query(value = "select concat(c.datee) as date, c.* " +
            "from calls c " +
            "inner join phone_lines pl on c.phone_number_from = pl.phone_number " +
            "where pl.id_user = ?1 and " +
            "c.datee BETWEEN DATE_ADD(concat(?2, ' 23:59:59.59'), INTERVAL -1 DAY) and concat( ?3, ' 23:59:59.59') " +
            "order by c.datee asc", nativeQuery = true)
    List<Call> getCallsBtwDatesByUser(Integer idUser, String startDate, String finalDate);

    @Query(value = "select c.* " +
            "from calls c " +
            "inner join phone_lines pl on c.phone_number_from = pl.phone_number " +
            "where pl.id_user = ?1 " +
            "order by c.id_bill asc", nativeQuery = true)
    List<Call> getCallsFromByUser(Integer idUser);

    @Query(value = "select distinct l.* " +
            "from localities l " +
            "inner join calls c on l.id_locality = c.id_locality_to " +
            "left join phone_lines pl on pl.phone_number = c.phone_number_from " +
            "where pl.id_user = ?1 " +
            "group by l.id_locality " +
            "order by count(c.id_locality_to) desc " +
            "limit 10 ", nativeQuery = true)
    List<Locality> getLocalitiesToByCallIdUser(Integer idUser);
}
