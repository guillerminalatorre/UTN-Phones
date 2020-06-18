package com.utn.utnphones.services;

import com.utn.utnphones.models.Locality;
import com.utn.utnphones.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalityService {
    private final LocalityRepository localityRepository;

    @Autowired
    public LocalityService (final LocalityRepository localityRepository){
        this.localityRepository = localityRepository;
    }

    //Solo GETS, no quiero que se agreguen ni que se modifiquen localidades.

    public List<Locality> getLocalities(){
        return localityRepository.findAll();
    }

    public Locality getLocalityById(Integer idlocality) {
        //Agregar exception si es null;
        Locality locality = new Locality();

        locality = localityRepository.findById(idlocality).get();

        return locality;
    }

    public List<Locality>  getLocalitiesByName(String name){

        List<Locality> reply = new ArrayList<Locality>();

        reply = localityRepository.findByName(name);


        return reply;
    }

    public void addLocality(Locality locality) {
        localityRepository.save(locality);
    }

    public List<Locality> getLocalitiesToByCallIdUser(Integer idUser) {
        return this.localityRepository.getLocalitiesToByCallIdUser(idUser);
    }
}
