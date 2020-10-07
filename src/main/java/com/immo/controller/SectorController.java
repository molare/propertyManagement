package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Sector;
import com.immo.service.SectorService;
import com.immo.service.TwonService;
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
public class SectorController {

    @Autowired
    private SectorService sectorService;

    //api pour recuperer la liste
    @RequestMapping(value = "/listSector", method = RequestMethod.GET)
    public ResponseData getAllSector(){
        List<Sector> listCom = sectorService.getAll();
        return new ResponseData(true,listCom);
    }

    //api pour ajouter
    @PostMapping("/saveSector")
    public ResponseData addSector(@Valid @RequestBody Sector sector){
        ResponseData json=null;
        try{
        Sector c =  sectorService.add(sector);
        return new ResponseData(true, c);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour modifier
    @PutMapping("/updateSector/{id}")
    public ResponseData updateSector(@Valid @RequestBody Sector sector,@PathVariable int id){
        ResponseData json=null;
        try{
        sector.setId(id);
        Sector c =  sectorService.update(sector);
        json= new ResponseData(true, c);

    }catch (Exception ex){
        json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
    }
        return json;
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/findSector/{id}")
    public ResponseData findSector(@PathVariable int id){
        Sector ci = sectorService.findById(id);
       return new ResponseData(true, ci);
    }

    //api pour supprimer un element par id
    @DeleteMapping(value = "/deleteSector/{id}")
    public ResponseData deleteSector(@PathVariable int id){
        ResponseData json=null;
        try {
            sectorService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donn&eacute;e car elle est li&eacute;e ailleurs",ex.getCause());
        }
        return json;
    }
}
