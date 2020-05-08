package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query(value = "select * from provinces p where p.name like %?1% order by p.name asc", nativeQuery = true)
    List<Province> findByName(String name);
}

