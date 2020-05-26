package com.utn.utnphones.services;

import com.utn.utnphones.models.Province;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.repositories.ProvinceRepository;
import com.utn.utnphones.repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Tariff> getTariffByLocalityFrom(Integer idLocalityFrom) {
        //Agregar exception si es null;
        List<Tariff> tariffs = new ArrayList<Tariff>();

        tariffs = tariffRepository.findByIdLocalityFrom(idLocalityFrom);

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
