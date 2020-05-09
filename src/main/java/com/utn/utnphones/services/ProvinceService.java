package com.utn.utnphones.services;

import com.utn.utnphones.models.Province;
import com.utn.utnphones.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService (final ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;
    }

    //Solo GETS, no quiero que se agreguen ni que se modifiquen provincias.

    public List<Province> getProvinces(){
        return provinceRepository.findAll();
    }

    public Province getProvinceById(Integer idProvince) {
        //Agregar exception si es null;
        Province province = new Province();

        province = provinceRepository.findById(idProvince).get();

        return province;
    }

    public List<Province>  getProvincesByName(String name){

        List<Province> reply = new ArrayList<Province>();

        reply = provinceRepository.findByName(name);


        return reply;
    }
}
