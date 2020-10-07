package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.TypeProperty;
import com.immo.service.TypePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TypePropertyController {

    @Autowired
    private TypePropertyService typePropertyService;

    //api pour recuperer la liste
    @GetMapping(value = "/listTypeProperty")
    public ResponseData getAllTypeProperty(){
        List<TypeProperty> newComList =typePropertyService.getAll();
        return new ResponseData(true,newComList);
    }

    //api pour ajouter
    @PostMapping(value = "/saveTypeProperty")
    public ResponseData addTypeProperty(@Valid @RequestBody TypeProperty typeProperty){
        TypeProperty c =  typePropertyService.add(typeProperty);
        return new ResponseData(true, c);
    }

    //api pour modifier
    @PutMapping(value = "/updateTypeProperty/{id}")
    public ResponseData updateTypeproperty(@Valid @RequestBody TypeProperty typeProperty,@PathVariable int id){
        TypeProperty ci = typePropertyService.findById(id);
        TypeProperty c =  typePropertyService.add(ci);
        return new ResponseData(true, c);
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/findTypeProperty/{id}")
    public ResponseData findTypeProperty(@PathVariable int id){
        TypeProperty ci = typePropertyService.findById(id);
       return new ResponseData(true, ci);
    }

    //api pour supprimer un elemnt par id
    @DeleteMapping(value = "/deleteTypeProperty/{id}")
    public ResponseData deleteTypeProperty(@PathVariable int id){
        ResponseData json=null;
        try {
            typePropertyService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }
}
