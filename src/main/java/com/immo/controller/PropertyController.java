package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Property;
import com.immo.service.PropertyService;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olivier on 02/10/2019.
 */
@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    //api pour recuperer la liste
    @RequestMapping(value = "/listProperty", method = RequestMethod.GET)
    public ResponseData getAllProperty(){
        List<Property> listCom = propertyService.getAll();
        return new ResponseData(true,listCom);
    }


    //api pour ajouter
    @PostMapping(value = "/saveProperty")
    public ResponseData addProperty(@Valid @RequestBody Property property,@RequestParam("picture")MultipartFile file)throws Exception{
        ResponseData json=null;
        try {
            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                property.setImageName(fileName);
                property.setImage(bytes);
            }
            json = new ResponseData(true, property);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour modifier
    @PutMapping(value = "/updateProperty/{id}")
    public ResponseData updateProperty(@Valid @RequestBody Property property,@PathVariable int id,@RequestParam("editPicture")MultipartFile file){
        ResponseData json=null;
        try {
            property.setId(id);
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                property.setImageName(fileName);
                property.setImage(bytes);
            }else{
                property.setImage(propertyService.findById(id).getImage());
                property.setImageName(propertyService.findById(id).getImageName());
            }

            json = new ResponseData(true, property);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/findProperty/{id}")
    public ResponseData findProperty(Locale locale,@ModelAttribute Property property,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Property ci = propertyService.findById(id);
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

    //api pour supprimer
    @DeleteMapping(value = "/deleteProperty/{id}")
    public ResponseData deleteProperty(@PathVariable int id){
        ResponseData json=null;
        try {
        propertyService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    //api pour compter les elemnts
    @PostMapping(value = "/countProperty")
    public ResponseData countProperty(){
        ResponseData json=null;
        try {
            int count = propertyService.countProperty();
            json = new ResponseData(true, count);
        }catch (Exception ex){
            json = new ResponseData(false,"erreur serveur",ex.getCause());
        }
        return json;
    }



    //api pour exporter la liste
    @PostMapping(value = "/property/export")
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        response.setContentType("application/pdf");

        int id =Integer.parseInt(request.getParameter("cpt"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Property> customers = propertyService.export(id, request);
        try {
            OutputStream out = response.getOutputStream();
            /*PropertyReporting p = new PropertyReporting(customers,authentication.getName());
            p.build(request).toPdf(out);*/
        } catch (IOException ex) {
            Logger.getLogger(PropertyController.class.getName()).log(Level.SEVERE, null, ex);
        } /* catch (DRException ex) {
            Logger.getLogger(PropertyController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
