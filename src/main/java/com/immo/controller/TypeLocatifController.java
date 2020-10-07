package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.TypeLocatif;
import com.immo.service.TypeLocatifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TypeLocatifController {

    @Autowired
    private TypeLocatifService typeLocatifService;

    //api pour recuperer la liste
    @GetMapping(value = "/listTypeLocatif")
    public ResponseData getAllTypelocatif(){
        List<TypeLocatif> listCom = typeLocatifService.getAll();
        return new ResponseData(true,listCom);
    }

    //api pour ajouter
    @PostMapping(value = "/saveTypeLocatif")
    public ResponseData addTypelocatif(@Valid @RequestBody TypeLocatif typeLocatif){

        TypeLocatif c =  typeLocatifService.add(typeLocatif);
        return new ResponseData(true, c);
    }

    //api pour modifier
    @PostMapping(value = "/updateTypeLocatif/{id}")
    public ResponseData updateTypelocatif(@Valid @RequestBody TypeLocatif typeLocatif,@PathVariable int id){
        TypeLocatif ci = typeLocatifService.findById(id);
        TypeLocatif c =  typeLocatifService.add(typeLocatif);
        return new ResponseData(true, c);
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/findTypeLocatif/{id}")
    public ResponseData findTypelocatif(@PathVariable int id){
        TypeLocatif ci = typeLocatifService.findById(id);
       return new ResponseData(true, ci);
    }

    //api pour supprimer un element par id
    @DeleteMapping(value = "/deleteTypeLocatif/{id}")
    public ResponseData deleteTypelocatif(@PathVariable int id){
        ResponseData json=null;
        try {
            typeLocatifService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;

    }
}
