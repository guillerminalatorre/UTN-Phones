package com.utn.utnphones.repositories;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.projections.TariffsByLocalityFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "select lf.id_locality as id1_1_1, lf.name as name2_1_1, t.id_tariff as tariff3_1_1 " +
            "from localities lf " +
            "inner join tariffs t on t.id_locality_from = lf.id_locality " +
            "inner join localities lt on t.id_locality_to = lt.id_locality where lf.id_locality = ?1;", nativeQuery = true)
    List<Locality> getByIdLocalityFrom(Integer idLocalityFrom);

    @Query(value = "select lf.id_locality as 'IdLocalityFrom', lf.name as 'NameLtyFrom', t.id_tariff as 'IdTariff'," +
            " t.cost_price as 'CostPrice', t.price as 'Price', lt.id_locality as 'IdLocalityTo', lt.name as 'NameLtyTo' "/*
            "select lf.* as 'LocalityFrom', lt.* as 'LocalityTo', t.* as 'Tariff'"*/+
            "from localities lf " +
            "right join tariffs t on t.id_locality_from = lf.id_locality " +
            "right join localities lt on t.id_locality_to = lt.id_locality " +
            "where lf.id_locality = ?1" +
            " order by lt.id_locality asc", nativeQuery = true)
    List<TariffsByLocalityFrom> findByIdLocalityFrom(Integer idLocalityFrom);

    @Query(value = "select t.* from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Tariff getById (Integer idTariff);

    @Query(value = "select t.price, t.id_locality_from from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Float findPriceById(Integer idTariff);

    @Query(value = "select t.cost_price from tariffs t where t.id_tariff = ?1", nativeQuery = true)
    Float findCostById(Integer idTariff);
}
