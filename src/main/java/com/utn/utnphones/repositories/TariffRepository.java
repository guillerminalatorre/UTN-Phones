package com.utn.utnphones.repositories;

import com.utn.utnphones.models.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "select t.* from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Tariff getById (Integer idTariff);

    @Query(value = "select t.* from tariffs t where t.id_locality_from = ?1 and t.id_locality_to = ?2 ", nativeQuery = true)
    Tariff getTariffByLocalityFromTo(Integer idLocalityFrom, Integer idLocalityTo);
}
