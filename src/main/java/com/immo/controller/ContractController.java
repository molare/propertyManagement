package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Contract;
import com.immo.entities.PayRoll;
import com.immo.service.*;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private PayRollService payRollService;

    //api pour recuperer la liste
    @GetMapping(value = "/listContrat")
    public ResponseData getAllContrat(){
        List<Contract> listCom = contractService.getAll();
        return new ResponseData(true,listCom);
    }

    // api pour ajouter
    @PostMapping(value = "/saveContrat")
    public ResponseData addContrat(@Valid @RequestBody Contract contrat, BindingResult result,@RequestParam("picture")MultipartFile file,HttpServletRequest request)throws Exception{
        ResponseData json=null;
        try {
            json = contractService.addContrat(contrat,result,file,request);

        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a été dupliquée ou erroné",ex.getCause());
        }
        return json;
    }

    // api pour modifier
    @RequestMapping(value = "/updateContrat/{idContrat}", method = RequestMethod.POST,headers="Accept=*/*",produces="application/json;charset=UTF-8")
    public ResponseData updateContrats(Locale locale, @ModelAttribute Contract cont,BindingResult result, @PathVariable int idContrat,@RequestParam("editPicture")MultipartFile file,HttpServletRequest request)throws Exception{

        ResponseData json=null;
     try{

             json = contractService.updateContrat(locale,cont,result,idContrat,file,request);


        }catch (Exception ex){
         System.out.println("erreur "+ex.getMessage());
         json = new ResponseData(false,"une valeur a été dupliquée ou erronée",ex.getMessage());
        }
        return json;
    }

    //api pour recuperer un element par id
    @RequestMapping(value = "/findContrat/{id}", method = RequestMethod.GET)
    public ResponseData findContrat(Locale locale,@ModelAttribute Contract contrat,@PathVariable int id, BindingResult result,HttpServletRequest request){
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

        Contract ci = contractService.findById(id);
        if(ci.getImage() != null){
            byte[] encodeBase64 = Base64.encodeBase64(ci.getImage());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String img = "<img style=\"width:200px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            ci.setImageTransient(img);
        }else{
            ci.setImageTransient(null);
        }
       return new ResponseData(true, ci);
    }

    //api pour supprimer un element par id
    @RequestMapping(value = "/deleteContrat/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteContrat(@PathVariable int id,HttpServletRequest request){
        ResponseData json=null;
        try {

            List<PayRoll> listPayRoll = payRollService.findByContrat(contractService.findById(id));
            if(!listPayRoll.isEmpty()){
               for(PayRoll  pr : listPayRoll){
                   payRollService.delete(pr.getId());
               }
            }
        contractService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }


}
