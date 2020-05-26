package com.utn.utnphones.controllers;
import java.util.List;
import com.utn.utnphones.models.LineType;
import com.utn.utnphones.services.LineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public LineType getLineTypeById(Integer idLineType){
        return lineTypeService.getLineTypeById(idLineType);
    }

    @GetMapping("/-digitsQty={idLineType}")
    public Integer getDigitsQtyById( Integer idLineType){
        return lineTypeService.getDigitsQtyById(idLineType);
    }


}
