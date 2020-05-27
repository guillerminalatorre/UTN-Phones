package com.utn.utnphones.controllers;

import com.utn.utnphones.models.Locality;
import com.utn.utnphones.services.LocalityService;
import com.utn.utnphones.services.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locality")
public class LocalityController {
    private final LocalityService localityService;

    @Autowired
    public LocalityController(final LocalityService localityService){
        this.localityService = localityService;
    }

    @GetMapping("/")
    public List<Locality> getLocalities(){
        return localityService.getLocalities();
    }

    @GetMapping("/{idLocality}")
    public Locality getLocalityById( @PathVariable(value = "idLocality", required = true) Integer idLocality){

        Locality reply = new Locality();

        reply = localityService.getLocalityById(idLocality);

        return reply;
    }

    @GetMapping("/n={name}")
    public List<Locality> getLocalitysByName(@PathVariable(value = "name", required = true) String name){

        List<Locality> reply = new ArrayList<Locality>();

        reply = localityService.getLocalitiesByName(name);

        return reply;
    }
    @PostMapping("/")
    public void addLocality(@RequestBody Locality locality){
        localityService.addLocality(locality);
    }
}
