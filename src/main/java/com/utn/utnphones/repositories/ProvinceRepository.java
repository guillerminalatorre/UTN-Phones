package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query(value = "select * from v_prov_by_name where name like %?1% order by name asc", nativeQuery = true)
    List<String> findByNameNames(String name);

    @Query(value = "select * from provinces where name like %?1% order by id_province", nativeQuery = true)
    List<Province> findByName(String name);

    @Query(value = "select * from v_prov_by_name order by name asc", nativeQuery = true)
    List<String> findAllNames();
}

