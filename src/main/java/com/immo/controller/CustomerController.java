package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Customer;
import com.immo.service.CustomerService;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olivier on 09/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //api pour recuperer la liste
    @GetMapping(value = "/listCustomer")
    public ResponseData getAllCustomer(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        List<Customer> listCustomer = customerService.getAll();
        return new ResponseData(true, listCustomer);
    }

    //api pour ajouter
    @PostMapping(value = "/saveCustomer")
    public ResponseData addCustomer(@Valid @RequestBody Customer customer,@RequestParam("picture")MultipartFile file)throws Exception{
        ResponseData json=null;
        try {
            if(file.getSize()>0){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                customer.setImageName(fileName);
                customer.setImage(bytes);
            }
            Customer p = customerService.add(customer);
            json = new ResponseData(true, p);

        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour modifier
    @PutMapping(value = "/updateCustomer/{id}")
    public ResponseData updateCustomer(@Valid @RequestBody Customer customer, @PathVariable int id,@RequestParam("editPicture")MultipartFile file,HttpServletRequest request)throws Exception{
        ResponseData json=null;

        try {
            customer.setId(id);
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                customer.setImageName(fileName);
                customer.setImage(bytes);
            }else{
                customer.setImage(customerService.findById(id).getImage());
                customer.setImageName(customerService.findById(id).getImageName());
            }

            Customer p = customerService.update(customer);
            json = new ResponseData(true, p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    //api pour recuperer un element par id
    @GetMapping(value = "/customer/{id}")
    public ResponseData getCustomer(@PathVariable int id){
        Customer customer = customerService.findById(id);
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

        if(customer.getImage() != null){
            byte[] encodeBase64 = Base64.encodeBase64(customer.getImage());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String img = "<img style=\"width:200px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            customer.setImageTransient(img);
        }else{
            customer.setImageTransient(null);
        }
        return new ResponseData(true, customer);
    }

    //api pour supprimer un elemnt par id
    @DeleteMapping(value = "/deleteCustomer/{id}")
    public ResponseData deleteCustomer(@PathVariable int id){
        ResponseData json;
        try {
            customerService.delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer car cette donn&eacute;e est utilis&eacute;e ailleurs",null);
        }
        return json;
    }

    //api pour compter le nombre de clients
    @GetMapping(value = "/countCustomer")
    public ResponseData countCustomer(){
        ResponseData json=null;
        try {
            int count = customerService.countLocater();
            json = new ResponseData(true, count);
        }catch (Exception ex){
            json = new ResponseData(false,"erreur serveur",ex.getCause());
        }
        return json;
    }

}
