package com.utn.utnphones.controllers;

import com.utn.utnphones.exceptions.IdLtyFromTariffsNotFoundException;
import com.utn.utnphones.exceptions.TariffNotExistsException;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.projections.TariffsByLocalityFrom;
import com.utn.utnphones.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(final TariffService tariffService){
        this.tariffService = tariffService;
    }

    @GetMapping("/")
    public List<Tariff> getTariffs(){
        return tariffService.getTariffs();
    }

    @GetMapping("/{idTariff}")
    public Tariff getTariffById( @PathVariable(value = "idTariff", required = true) Integer idTariff) throws TariffNotExistsException {

        Tariff reply = new Tariff();

        reply = tariffService.getTariffById(idTariff);

        return reply;
    }

    @GetMapping("/ltyfrom={idLocalityFrom}")
    public List<TariffsByLocalityFrom> getTariffByLocalityFrom(@PathVariable(value = "idLocalityFrom", required = true)Integer idLocalityFrom) throws IdLtyFromTariffsNotFoundException {
        return tariffService.getTariffByLocalityFrom( idLocalityFrom);
    }

    @GetMapping("/{idTariff}/price")
    public Float getTariffPriceById(@PathVariable(value = "idTariff", required = true) Integer idTariff){
        return tariffService.getTariffPriceById(idTariff);
    }

    @GetMapping("/{idTariff}/cost")
    public Float getTariffCostById(@PathVariable(value = "idTariff", required = true) Integer idTariff){
        return tariffService.getTariffCostById(idTariff);
    }

    @PostMapping("/")
    public void addTariff(@RequestBody Tariff tariff){this.tariffService.addTariff(tariff);}

}
