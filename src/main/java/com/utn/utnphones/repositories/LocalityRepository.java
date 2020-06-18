package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {

    @Query(value = "select * from localities l where l.name like %?1% order by l.name asc", nativeQuery = true)
    List<Locality> findByName(String name);

    @Query(value = "select distinct l.* " +
            "from localities l " +
            "inner join calls c on l.id_locality = c.id_locality_to " +
            "left join phone_lines pl on pl.phone_number = c.phone_number_from " +
            "where pl.id_user = ?1 " +
            "group by l.id_locality " +
            "order by count(c.id_locality_to) desc " +
            "limit 10 ", nativeQuery = true)
    List<Locality> getLocalitiesToByCallIdUser(Integer idUser);

    @Query(value = "select * from localities l where l.id_locality = ?1", nativeQuery = true)
    Locality getById(Integer idLocality);
}
