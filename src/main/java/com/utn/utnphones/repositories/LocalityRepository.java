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

    /*@Query(value = "select * from v_localities order by id", nativeQuery = true)
    List<Locality> findAllLocalities();*/
}
