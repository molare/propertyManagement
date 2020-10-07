package com.immo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by olivier on 01/10/2019.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class MainController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String index(Model model){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
     public String login(Model model){
        return "lockscreen";
    }

    @RequestMapping(value = "/com", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String com(Model model){
        return "commune";
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String city(Model model){
        return "quartier";
    }

    @RequestMapping(value = "/typeBien", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String typeLog(Model model){
        return "typeBien";
    }

    @RequestMapping(value = "/bien", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String log(Model model){
        return "bien";
    }

    @RequestMapping(value = "/contrat", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String contrat(Model model){
        return "contrat";
    }

    @RequestMapping(value = "/moyenPay", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moyenPay(Model model){
        return "moyenpay";
    }

    @RequestMapping(value = "/statutPay", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String statutPay(Model model){
        return "statutpay";
    }

    @RequestMapping(value = "/typePay", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String typePaye(Model model){
        return "typepay";
    }

    @RequestMapping(value = "/locater", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String locataire(Model model){
        return "locataire";
    }

    @RequestMapping(value = "/civility", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String civility(Model model){
        return "civility";
    }

    @RequestMapping(value = "/typeProperty", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String typeProperty(Model model){
        return "typeProperty";
    }

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String property(Model model){
        return "property";
    }

    @RequestMapping(value = "/locative", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String locative(Model model){
        return "locative";
    }

    @RequestMapping(value = "/typeLocative", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String typeLocative(Model model){
        return "typeLocative";
    }

    @RequestMapping(value = "/devis", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String devis(Model model){
        return "devis";
    }

    @RequestMapping(value = "/payRoll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String payRoll(Model model){
        return "payroll";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String report(Model model){
        return "reporting";
    }

    @RequestMapping(value = "/twon", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
      public String twon(Model model){
        return "twon";
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String country(Model model){
        return "country";
    }

    @RequestMapping(value = "/enterpriseType", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String enterpriseType(Model model){
        return "enterpriseType";
    }


    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String enterprise(Model model){
        return "enterprise";
    }
}
