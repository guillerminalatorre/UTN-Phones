package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "select * from tariffs t where t.id_locality_from = ?1 order by t.id_tariff asc", nativeQuery = true)
    List<Tariff> findByIdLocalityFrom(Integer idLocalityFrom);

    @Query(value = "select t.price from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Float findPriceById(Integer idTariff);

    @Query(value = "select t.cost_price from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Float findCostById(Integer idTariff);
}
