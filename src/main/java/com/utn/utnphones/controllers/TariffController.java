package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(final TariffService tariffService){
        this.tariffService = tariffService;
    }

    public ResponseEntity<List<Tariff>> getTariffs(){
        return ResponseEntity.ok(tariffService.getTariffs());
    }

    public ResponseEntity<Tariff> getTariffByLocalityFromTo(Integer idLocalityFrom, Integer idLocalityTo) throws TariffNotExistsException {
        return ResponseEntity.ok(this.tariffService.getTariffByLocalityFromTo(idLocalityFrom, idLocalityTo));
    }
}
