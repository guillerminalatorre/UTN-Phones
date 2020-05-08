package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Province;
import com.utn.utnphones.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @GetMapping("/{idProvince}")
    public Province getProvinceById(@PathVariable(value = "idProvince", required = true) @Min(value = 1, message = "No existe la provincia buscada") @Max(value = 23, message = "No existe la provincia buscada") Integer idProvince){

        return provinceService.getProvinceById(idProvince);
    }

    @GetMapping("/n={name}")
    public List<Province> getProvincesByName(@PathVariable(value = "name", required = true) String name){
        return provinceService.getProvincesByName(name);
    }
}
