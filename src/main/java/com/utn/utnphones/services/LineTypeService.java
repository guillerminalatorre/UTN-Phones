package com.utn.utnphones.services;

import com.utn.utnphones.models.LineType;
import com.utn.utnphones.repositories.LineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineTypeService {
    private final LineTypeRepository lineTypeRepository;

    @Autowired
    public LineTypeService (final LineTypeRepository lineTypeRepository){
        this.lineTypeRepository = lineTypeRepository;
    }

    public List<LineType> getLineTypes(){
        return lineTypeRepository.findAll();
    }

    public LineType getLineTypeById(Integer idLineType){
        return lineTypeRepository.findById(idLineType).get();
    }

    public Integer getDigitsQtyById( Integer idLineType){
        return lineTypeRepository.findDigitsQtyById(idLineType);
    }

    public void addLineType(LineType lineType) {
        this.lineTypeRepository.save(lineType);
    }
}
