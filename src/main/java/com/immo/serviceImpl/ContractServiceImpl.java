package com.immo.serviceImpl;


import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Contract;
import com.immo.entities.PayRoll;
import com.immo.repositories.ContractRepository;
import com.immo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("contratService")
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private LocatifService locatifService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PayRollService payRollService;

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract add(Contract contrat) {
        return contractRepository.save(contrat);
    }

    @Override
    public Contract update(Contract contrat){
        if(contrat.getId() ==0){
            return contractRepository.save(contrat);
        }
        return contractRepository.saveAndFlush(contrat);
    }

    @Override
    public Contract findById(int id) {
        return contractRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        contractRepository.deleteById(id);
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ResponseData addContrat(Contract contrat, BindingResult result, MultipartFile file, HttpServletRequest request) {
        ResponseData json=null;
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {


            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                contrat.setImageName(fileName);
                contrat.setImage(bytes);
            }

           /* contrat.setName(request.getParameter("name").toUpperCase());
            contrat.setAmount(Double.parseDouble(request.getParameter("amount")));
            contrat.setAdvanceMonth(Integer.parseInt(request.getParameter("advance")));
            contrat.setAgenceMonth(Integer.parseInt(request.getParameter("agence")));
            contrat.setMonthNber(Integer.parseInt(request.getParameter("monthNber")));
            contrat.setStatusContrat(request.getParameter("statusContrat"));
            contrat.setFirstQuittance(Double.parseDouble(request.getParameter("firstQuittance")));
            contrat.setStartBailDate(sdf.parse(request.getParameter("startBailDate")));
            contrat.setLocater(customerService.findById(Integer.parseInt(request.getParameter("locater"))));
            contrat.setLocative(locatifService.findById(Integer.parseInt(request.getParameter("locative"))));
            contrat.setMoyenPay(moyenPayService.findById(Integer.parseInt(request.getParameter("moyen"))));
            contrat.setStatutPay(statutPayService.findById(Integer.parseInt(request.getParameter("statut"))));
            contrat.setCommentary(request.getParameter("commentary"));
            contrat.setRestCaution(contrat.getAmount()-(contrat.getAdvanceMonth()+contrat.getAgenceMonth())*(contrat.getLocative().getAmount()));*/


            Contract c = add(contrat);




           if(c!=null){
              /* String[] monthAbbrev = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Decembre"};

               for(int i= 0; i < contrat.getAdvanceMonth(); i++){
                    PayRoll payRollScaleDetail = new PayRoll();

                    payRollScaleDetail.setContrat(c);
                   String[] startDateTab = request.getParameter("startBailDate").split("-");

                   Calendar calendarStart=Calendar.getInstance();
                   calendarStart.set(Calendar.YEAR,Integer.parseInt(startDateTab[2]));
                   calendarStart.set(Calendar.MONTH,Integer.parseInt(startDateTab[1])-1);
                   calendarStart.set(Calendar.DAY_OF_MONTH,Integer.parseInt(startDateTab[0]));

                   Calendar calendarEnd=Calendar.getInstance();
                   calendarEnd.setTime(calendarStart.getTime());
                   calendarStart.set(Calendar.YEAR,Integer.parseInt(startDateTab[2]));
                   calendarEnd.set(Calendar.MONTH,Integer.parseInt(startDateTab[1])+i-1);;
                   calendarEnd.set(Calendar.DAY_OF_MONTH,Integer.parseInt(startDateTab[0]));

                   String monthId = df.format(calendarEnd.getTime());
                   payRollScaleDetail.setName(monthAbbrev[Integer.parseInt(monthId.split("-")[1])-1]);
                    payRollScaleDetail.setEndDate(calendarEnd.getTime());
                   payRollScaleDetail.setAmount(c.getLocative().getAmount()+c.getLocative().getCharge());
                   payRollScaleDetail.setStatusId(1);
                   payRollScaleDetail.setCautionStatut(1);
                   PayRoll p = payRollService.add(payRollScaleDetail);
                }*/

               json = new ResponseData(true, c);
            }

        }catch (Exception ex){

            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    @Override
    public List<Contract> export(int cpt, HttpServletRequest request) {
        List<Contract> cusList = new ArrayList<Contract>();
        Contract cus = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            cus = contractRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

            //cus.setDateTransient(df.format(cus.getDate()));

            cusList.add(cus);
        }
        return cusList;
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ResponseData updateContrat(Locale locale, Contract cont, BindingResult result, int idContrat, MultipartFile file, HttpServletRequest request) {
        ResponseData json=null;
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Contract contrat = findById(idContrat);
            /*int oldValAdvance =contrat.getAdvanceMonth();
            List<PayRoll> plist = payRollService.findByContrat(contrat);


            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                contrat.setImageName(fileName);
                contrat.setImage(bytes);
            }

            contrat.setName(request.getParameter("name").toUpperCase());
            contrat.setAmount(Double.parseDouble(request.getParameter("amount")));
            contrat.setAdvanceMonth(Integer.parseInt(request.getParameter("advance")));
            contrat.setAgenceMonth(Integer.parseInt(request.getParameter("agence")));
            contrat.setMonthNber(Integer.parseInt(request.getParameter("monthNber")));
            contrat.setStatusContrat(request.getParameter("statusContrat"));
            contrat.setFirstQuittance(Double.parseDouble(request.getParameter("firstQuittance")));
            contrat.setStartBailDate(sdf.parse(request.getParameter("editStartBailDate")));
            contrat.setLocater(customerService.findById(Integer.parseInt(request.getParameter("locater"))));
            contrat.setLocative(locatifService.findById(Integer.parseInt(request.getParameter("locative"))));
            contrat.setMoyenPay(moyenPayService.findById(Integer.parseInt(request.getParameter("moyen"))));
            contrat.setStatutPay(statutPayService.findById(Integer.parseInt(request.getParameter("statut"))));
            contrat.setCommentary(request.getParameter("commentary"));
            contrat.setRestCaution(contrat.getAmount()-(contrat.getAdvanceMonth()+contrat.getAgenceMonth())*(contrat.getLocative().getAmount()));*/


            Contract c = update(contrat);



            /*if(c!=null && Integer.parseInt(request.getParameter("advance")) !=oldValAdvance){

                for(PayRoll pRoll :plist){
                    payRollService.delete(pRoll.getId());
                }

                String[] monthAbbrev = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Decembre"};

                for(int i= 0; i < contrat.getAdvanceMonth(); i++){
                    PayRoll payRollScaleDetail = new PayRoll();

                    payRollScaleDetail.setContrat(c);
                    String[] startDateTab = request.getParameter("editStartBailDate").split("-");

                    Calendar calendarStart=Calendar.getInstance();
                    calendarStart.set(Calendar.YEAR,Integer.parseInt(startDateTab[2]));
                    calendarStart.set(Calendar.MONTH,Integer.parseInt(startDateTab[1])-1);
                    calendarStart.set(Calendar.DAY_OF_MONTH,Integer.parseInt(startDateTab[0]));

                    Calendar calendarEnd=Calendar.getInstance();
                    calendarEnd.setTime(calendarStart.getTime());
                    calendarStart.set(Calendar.YEAR,Integer.parseInt(startDateTab[2]));
                    calendarEnd.set(Calendar.MONTH,Integer.parseInt(startDateTab[1])+i-1);;
                    calendarEnd.set(Calendar.DAY_OF_MONTH,Integer.parseInt(startDateTab[0]));

                    String monthId = df.format(calendarEnd.getTime());
                    payRollScaleDetail.setName(monthAbbrev[Integer.parseInt(monthId.split("-")[1])-1]);
                    payRollScaleDetail.setEndDate(calendarEnd.getTime());
                    payRollScaleDetail.setAmount(c.getLocative().getAmount()+c.getLocative().getCharge());
                    payRollScaleDetail.setStatusId(1);
                    payRollScaleDetail.setCautionStatut(1);
                    PayRoll p = payRollService.add(payRollScaleDetail);
                }

                json = new ResponseData(true, c);
            }else{
                json = new ResponseData(true, c);
            }*/

        }catch (Exception ex){

            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

}
