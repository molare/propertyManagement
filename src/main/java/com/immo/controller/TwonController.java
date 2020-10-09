package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Twon;
import com.immo.service.TwonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TwonController {

    @Autowired
    private TwonService twonService;

    //api pour recuperer la liste
    @GetMapping("/listTwon")
    public ResponseData getAllTwon(){
        List<Twon> listCom = twonService.getAll();
        return new ResponseData(true,listCom);
    }

    //api pour ajouter
    @PostMapping("/saveTwon")
    public ResponseData addTwon(@Valid @RequestBody Twon twon){
        Twon c =  twonService.add(twon);
        return new ResponseData(true, c);
    }

    //api pour modifier
    @PutMapping("/updateTwon/{id}")
    public ResponseData updateTwon(@Valid @RequestBody Twon twon, @PathVariable int id){
        ResponseData json =null;
        try{
            twon.setId(id);
            Twon c =  twonService.update(twon);
            json =new ResponseData(true, c);
        }catch (Exception ex){
            System.out.println("erreur de modif_____________"+ex.getMessage()+" erreur2 "+ex.getStackTrace());
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/findTwon/{id}")
    public ResponseData findTwon(@PathVariable int id){
        Twon ci = twonService.findById(id);
       return new ResponseData(true, ci);
    }

    //api pour supprimer un element par id
    @DeleteMapping(value = "/deleteTwon/{id}")
    public ResponseData deleteTwon(@PathVariable int id){
        ResponseData json=null;
        try {
            twonService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est utilisée ailleurs",ex.getCause());
        }
        return json;
    }
}
