package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.IdLtyFromTariffsNotFoundException;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.projections.TariffsByLocalityFrom;
import com.utn.utnphones.repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TariffService {
    private final TariffRepository tariffRepository;

    @Autowired
    public TariffService (final TariffRepository tariffRepository){
        this.tariffRepository = tariffRepository;
    }

    public List<Tariff> getTariffs(){
        return tariffRepository.findAll();
    }

    public Tariff getTariffById(Integer idTariff) {
        //Agregar exception si es null;
        Tariff tariff = new Tariff();

        tariff = tariffRepository.findById(idTariff).get();

        return tariff;
    }

    public List<TariffsByLocalityFrom> getTariffByLocalityFrom(Integer idLocalityFrom) throws IdLtyFromTariffsNotFoundException{

        List<TariffsByLocalityFrom> tariffs = new ArrayList<TariffsByLocalityFrom>();

        tariffs = tariffRepository.findByIdLocalityFrom(idLocalityFrom);

        if(tariffs.isEmpty()){
            throw new IdLtyFromTariffsNotFoundException(idLocalityFrom);
        }

        /*Optional.ofNullable(tariffs).orElseThrow(() -> new IdLtyFromTariffsNotFoundException(idLocalityFrom));*/

        return tariffs;
    }

    public Float getTariffPriceById(Integer idTariff) {
        //Agregar exception si es null;

        Float price = tariffRepository.findPriceById(idTariff);

        return price;
    }

    public Float getTariffCostById(Integer idTariff) {
        //Agregar exception si es null;

        Float price = tariffRepository.findCostById(idTariff);

        return price;
    }

}
