package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Civility;
import com.immo.service.CivilityService;
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
public class CivilityController {

    @Autowired
    private CivilityService civilityService;
    //api pour la liste
    @GetMapping(value = "/listCivility")
    public ResponseData getAllCivility(){
        List<Civility> listCom = civilityService.getAll();
        return new ResponseData(true,listCom);
    }

    //api pour ajouter
    @PostMapping(value = "/saveCivility")
    public ResponseData addCivility(@Valid @RequestBody Civility civility){
        Civility c =  civilityService.add(civility);
        return new ResponseData(true, c);
    }

    //api pour modifier
    @PutMapping(value = "/updateCivility/{id}")
    public ResponseData updateCivility(Locale locale,@ModelAttribute Civility civility,@PathVariable int id){
        Civility ci = civilityService.findById(id);
        Civility c =  civilityService.add(ci);
        return new ResponseData(true, c);
    }
    //api pour recuperer un element par id
    @GetMapping(value = "/findCivility/{id}")
    public ResponseData findCivility(@Valid @RequestBody Civility civility,@PathVariable int id){
        Civility ci = civilityService.findById(id);
       return new ResponseData(true, ci);
    }
    //api pour supprimer
    @DeleteMapping(value = "/deleteCivility/{id}")
    public ResponseData deleteCivility(@PathVariable int id){

        ResponseData json=null;
        try {
            civilityService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }
}
