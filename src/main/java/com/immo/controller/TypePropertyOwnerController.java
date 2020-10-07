package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.TypePropertyOwner;
import com.immo.service.TypePropertyOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TypePropertyOwnerController {

    @Autowired
    private TypePropertyOwnerService typePropertyOwnerService;
    //api pour recuperer la liste
    @RequestMapping(value = "/listTypePropertyOwner", method = RequestMethod.GET)
    public ResponseData getAllTypePropertyOwner(){

        List<TypePropertyOwner> listCom = typePropertyOwnerService.getAll();
        return new ResponseData(true,listCom);
    }

    //api pour ajouter
    @PostMapping(value = "/saveTypePropertyOwner")
    public ResponseData addTypePropertyOwner(@Valid @RequestBody  TypePropertyOwner typePropertyOwner){
        TypePropertyOwner c =  typePropertyOwnerService.add(typePropertyOwner);
        return new ResponseData(true, c);
    }

    //api pour modifier
    @PutMapping(value = "/updateTypePropertyOwner/{id}")
    public ResponseData updateTypePropertyOwner(@Valid @RequestBody TypePropertyOwner typePropertyOwner,@PathVariable int id){
        TypePropertyOwner ci = typePropertyOwnerService.findById(id);
        TypePropertyOwner c =  typePropertyOwnerService.add(ci);
        return new ResponseData(true, c);
    }

    //api pour recuperer un elemnt par id
    @GetMapping(value = "/findTypePropertyOwner/{id}")
    public ResponseData findTypePropertyOwner(@PathVariable int id){
        TypePropertyOwner ci = typePropertyOwnerService.findById(id);
       return new ResponseData(true, ci);
    }

    //api pour supprimer un element par id
    @DeleteMapping(value = "/deleteTypePropertyOwner/{id}")
    public ResponseData deleteTypePropertyOwner(@PathVariable int typePropertyId){
        ResponseData json=null;
        try {
            typePropertyOwnerService.delete(typePropertyId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;

    }
}
