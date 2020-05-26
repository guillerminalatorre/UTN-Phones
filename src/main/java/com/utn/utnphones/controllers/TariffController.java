package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Province;
import com.utn.utnphones.models.Tariff;
import com.utn.utnphones.services.ProvinceService;
import com.utn.utnphones.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Tariff getTariffById( @PathVariable(value = "idTariff", required = true) Integer idTariff){

        Tariff reply = new Tariff();

        reply = tariffService.getTariffById(idTariff);

        return reply;
    }

    @GetMapping("/ltyfrom={idLocalityFrom}")
    public List<Tariff> getTariffByLocalityFrom(Integer idLocalityFrom){
        return tariffService.getTariffByLocalityFrom( idLocalityFrom);
    }

    @GetMapping("/-price={idTariff}")
    public Float getTariffPriceById(Integer idTariff){
        return tariffService.getTariffPriceById(idTariff);
    }

    @GetMapping("/-cost={idTariff}")
    public Float getTariffCostById(Integer idTariff){
        return tariffService.getTariffCostById(idTariff);
    }
}
