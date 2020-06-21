package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {

    @Query(value = "select * from localities l where l.id_locality = ?1", nativeQuery = true)
    Locality getById(Integer idLocality);
}
