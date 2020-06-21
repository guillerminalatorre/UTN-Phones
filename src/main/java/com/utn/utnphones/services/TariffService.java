package com.utn.utnphones.services;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Tariff> tariffs = new ArrayList<Tariff>();

        tariffs = tariffRepository.findAll();

        if(!tariffs.isEmpty()){
            return (List<Tariff>) ResponseEntity.ok(tariffs);
        }else{
            return (List<Tariff>) ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public Tariff getTariffByLocalityFromTo(Integer idLocalityFrom, Integer idLocalityTo) throws TariffNotExistsException {
        Tariff tariff = new Tariff();

        tariff = tariffRepository.getTariffByLocalityFromTo(idLocalityFrom, idLocalityTo);

        return  Optional.ofNullable(tariff).orElseThrow(() -> new TariffNotExistsException("Tariff do not exists"));
    }

}
