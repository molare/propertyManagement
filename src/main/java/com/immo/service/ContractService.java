package com.immo.service;

import com.immo.dataTableResponse.ResponseData;
import com.immo.entities.Contract;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
public interface ContractService {
    public List<Contract> getAll();
    public Contract add(Contract contrat);
    public Contract update(Contract contrat);
    public Contract findById(int id);
    public void delete(int id);
    public ResponseData addContrat(Contract contrat, BindingResult result,MultipartFile file,HttpServletRequest request);
    public List<Contract> export(int cpt, HttpServletRequest request);
    public ResponseData updateContrat(Locale locale, Contract cont,BindingResult result, int idContrat,MultipartFile file,HttpServletRequest request);

    }
