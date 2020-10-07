package com.immo.controller;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.PayRoll;
import com.immo.service.*;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olivier on 02/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PayRollController {


    @Autowired
    private PayRollService payRollService;


    //api pour recuper la liste
    @PostMapping(value = "/payRoll/findByContrat/{id}")
    public ResponseData findContrat(@PathVariable int id){
        List<PayRoll> pList = payRollService.findByContratByOrderByIdAsc(id);
        return new ResponseData(true, pList);
    }

    //api pour ajouter une paye
    @PostMapping(value = "/savePayRoll")
    public ResponseData savePayRoll(@Valid @RequestBody Locale locale, PayRoll payRoll, BindingResult result,HttpServletRequest request){
        ResponseData json=null;
        json = payRollService.addPayRoll(locale,payRoll,result,request);
        return json;
    }

    //api pour modifier une paye
    @PutMapping(value = "/updatePayRoll/{id}")
    public ResponseData updatePayRoll(Locale locale,@ModelAttribute PayRoll p, @PathVariable int id, BindingResult result,HttpServletRequest request){
        ResponseData json=null;
        json = payRollService.updatePayRoll(locale,p,id,result,request);
        return json;
    }

    //api pour recuperer une paye
    @GetMapping(value = "/findPayRoll/{id}")
    public ResponseData findPayRoll(@PathVariable int id){
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
        PayRoll p = payRollService.findById(id);
        p.setEndDateTransient(sdf.format(p.getEndDate()));
        return new ResponseData(true, p);
    }

    //api pour faire la somme des paiements
    @RequestMapping(value = "/sumPayRoll", method = RequestMethod.POST)
    public ResponseData countBien(){
        ResponseData json=null;
        try {
            double sum = payRollService.sumPayRoll();
            json = new ResponseData(true, sum);
        }catch (Exception ex){
            json = new ResponseData(false,"erreur serveur",ex.getCause());
        }
        return json;
    }

    //api pour construction des paiements
    @PostMapping(value = "/payRoll/charts")
    public ResponseData saleCharts(){
        List<Object>  firstPayRollCharts = payRollService.firstYearPayRollChart();
        List<Object>  secondPayRollCharts = payRollService.secondYearPayRollChart();
        List<Object>  threePayRollCharts = payRollService.threeYearPayRollChart();
        return new ResponseData(true,firstPayRollCharts,secondPayRollCharts,threePayRollCharts);
    }



}
