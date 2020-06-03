package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Province;
import com.utn.utnphones.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(final ProvinceService provinceService){
        this.provinceService = provinceService;
    }

    @GetMapping("/")
    public List<Province> getProvinces(){
        return provinceService.getProvinces();
    }

    @GetMapping("/names")
    public List<String> getProvinceNames(){
        return provinceService.getProvinceNames();
    }

    @GetMapping("/{idProvince}")
    public Province getProvinceById( @PathVariable(value = "idProvince", required = true) Integer idProvince){

        Province reply = new Province();

        reply = provinceService.getProvinceById(idProvince);

        return reply;
    }

    @GetMapping("/n={name}/names")
    public List<String> getProvincesByNameName(@PathVariable(value = "name", required = true) String name){

        List<String> reply = new ArrayList<String>();

        reply = provinceService.getProvincesByNameNames(name);

        return reply;
    }

    @GetMapping("/n={name}")
    public List<Province> getProvincesByName(@PathVariable(value = "name", required = true) String name){

        List<Province> reply = new ArrayList<Province>();

        reply = provinceService.getProvincesByName(name);

        return reply;
    }

    @PostMapping("/")
    public void addProvince(@RequestBody Province province){
        provinceService.addProvince(province);
    }
}
