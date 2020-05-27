package com.utn.utnphones.controllers;
import java.util.List;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.services.LineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/line-type")
public class LineTypeController {
    private final LineTypeService lineTypeService;

    @Autowired
    public LineTypeController(final LineTypeService lineTypeService){ this.lineTypeService = lineTypeService;}

    @GetMapping("/")
    public List<LineType> getLineTypes(){
        return lineTypeService.getLineTypes();
    }

    @GetMapping("/{idLineType}")
    public LineType getLineTypeById(@PathVariable(value = "idLineType", required = true) Integer idLineType){
        return lineTypeService.getLineTypeById(idLineType);
    }

    @GetMapping("/{idLineType}/digitsQty")
    public Integer getDigitsQtyById(@PathVariable(value = "idLineType", required = true) Integer idLineType){
        return lineTypeService.getDigitsQtyById(idLineType);
    }


}
