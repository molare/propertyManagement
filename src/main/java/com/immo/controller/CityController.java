package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.City;
import com.immo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    //Api pour recuperer la liste
    @RequestMapping(value = "/listCity", method = RequestMethod.GET)
    public ResponseData getAllCity() {
        List<City> listCom = cityService.getAll();
        return new ResponseData(true,listCom);
    }

    //Api pour ajout une commune
    @PostMapping("/saveCity")
    public ResponseData addCity(@Valid @RequestBody City city){
        ResponseData json = null;
        try{
            City c =  cityService.add(city);
            json = new ResponseData(true,c);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return  json;
    }
    //Api pour modifier une commune
    @PutMapping(value = "/updateCity/{id}")
       public ResponseData updateCity(@Valid @RequestBody City city,@PathVariable int id){
        ResponseData json=null;
        try{
        City c =  cityService.update(city);
        json = new ResponseData(true,c);

        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return  json;
    }

    //Api pour recuperer une commune par id
    @GetMapping(value = "/findCity/{idCity}")
    public ResponseData findCity(@PathVariable int idCity){
        City ci = cityService.findById(idCity);
        return new ResponseData(true,ci);
    }

    //Api pour supprimer une commune
    @DeleteMapping(value = "/deleteCity/{id}")
    public ResponseData deleteCity(@PathVariable int id){
        ResponseData json=null;
        try {
            cityService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est utilisée ailleurs",ex.getCause());
        }
        return json;
    }
}
