package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.PropertyOwner;
import com.immo.service.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PropertyOwnerController {

    @Autowired
    private PropertyOwnerService propertyOwnerService;

    //api pour recuperer la liste
    @GetMapping(value = "/listPropertyOwner")
    public ResponseData getAllPropertyOwner(){
        List<PropertyOwner> listPropertyOwner = propertyOwnerService.getAll();
        return new ResponseData(true, listPropertyOwner);
    }

    //api pour ajouter
    @PostMapping(value = "/savePropertyOwner")
    public ResponseData addPropertyOwner(@Valid @RequestBody PropertyOwner propertyOwner,@RequestParam("picture")MultipartFile file)throws Exception{
        ResponseData json=null;
        try {
            if(file.getSize()>0){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                propertyOwner.setImageName(fileName);
                propertyOwner.setImage(bytes);
            }
            PropertyOwner p = propertyOwnerService.add(propertyOwner);
            json = new ResponseData(true, p);

        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour modifier
    @PutMapping(value = "/updatePropertyOwner/{id}")
    public ResponseData updatePropertyOwner(@Valid @RequestBody PropertyOwner propertyOwner, @PathVariable int id,@RequestParam("editPicture")MultipartFile file)throws Exception{
        ResponseData json=null;
        propertyOwner.setId(id);
        try {
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                propertyOwner.setImageName(fileName);
                propertyOwner.setImage(bytes);
            }else{
                propertyOwner.setImage(propertyOwnerService.findById(id).getImage());
                propertyOwner.setImageName(propertyOwnerService.findById(id).getImageName());
            }
            PropertyOwner p = propertyOwnerService.update(propertyOwner);
            json = new ResponseData(true, p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour recuperer element par id
    @GetMapping(value = "/propertyOwner/{id}")
    public ResponseData getPropertyOwner(@PathVariable int id){
        PropertyOwner p = propertyOwnerService.findById(id);
        if(p.getImage() != null){
            byte[] encodeBase64 = Base64.encodeBase64(p.getImage());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String img = "<img style=\"width:200px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            p.setImageTransient(img);
        }else{
            p.setImageTransient(null);
        }
        return new ResponseData(true, p);
    }


    //api pour supprimer un element
    @DeleteMapping(value = "/deletePropertyOwner/{id}")
    public ResponseData deletePropertyOwner(@PathVariable int id){
        ResponseData json;
        try {
            propertyOwnerService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer car cette donnée est utilisée ailleurs",null);
        }
        return json;
    }
}
