package com.immo.serviceImpl;


import com.immo.entities.Locatif;
import com.immo.entities.City;
import com.immo.entities.Property;
import com.immo.repositories.LocatifRepository;
import com.immo.service.LocatifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("locatifService")
@Transactional
public class LocatifServiceImpl implements LocatifService {
    @Autowired
    private LocatifRepository locativeRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Locatif> getAll() {
        return locativeRepository.findAll();
    }

    @Override
    public Locatif add(Locatif locative) {
        return locativeRepository.save(locative);
    }

    @Override
    public Locatif update(Locatif locative) {
        if(locative.getId() ==0){
            return locativeRepository.save(locative);
        }
        return locativeRepository.saveAndFlush(locative);
    }

    @Override
    public Locatif findById(int id) {
        return locativeRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        locativeRepository.deleteById(id);
    }

    @Override
    public List<Locatif> findByProperty(Property property) {
        return null;
    }

    @Override
    public List<Locatif> findByCity(City city) {
        return null;
    }


    @Override
    public double garanty(int id) {
        String sql="SELECT (lo.amount) AS garanty FROM locative lo\n" +
                "WHERE lo.id ="+id;
        Query query = em.createNativeQuery(sql);
        return Double.parseDouble(query.getSingleResult()+"");
    }

    @Override
    public Locatif findByContrat(int id) {
        Locatif lo =null;
        try {
            String sql = "SELECT lo.id FROM contrat con, locative lo\n" +
                    "WHERE con.locative_id = lo.id\n" +
                    "AND lo.id="+ id;
            Query query = em.createNativeQuery(sql);
            lo = locativeRepository.findById(Integer.parseInt(query.getSingleResult() + ""));
        }catch (NoResultException ex){
            return null;
        }
        return lo;
    }

    @Override
    public List<Locatif> getLocativeNotInContrat() {
        String sql = "SELECT lo.id,lo.designation\n" +
                "FROM locative lo\n" +
                "WHERE lo.id NOT IN (SELECT lot.id\n" +
                "FROM locative lot, contrat cont\n" +
                "WHERE cont.locative_id = lot.id AND cont.status_contrat='1') ORDER BY lo.designation ASC \n";
        Query query = em.createNativeQuery(sql);
        List<Object[]> listLo = query.getResultList();
        List<Locatif> locativeList = new ArrayList<Locatif>();
        for(Object[] rs :listLo){
            Locatif lo = new Locatif();
            lo.setId(Integer.parseInt(rs[0]+""));
            lo.setDesignation(rs[1]+"");
            locativeList.add(lo);
        }
        return locativeList;
    }

    @Override
    public int countLocative() {
        String sql="SELECT COUNT(DISTINCT lo.id) AS nb FROM locative lo";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }
    }

    @Override
    public List<Locatif> export(int cpt, HttpServletRequest request) {
        List<Locatif> list = new ArrayList<Locatif>();
        Locatif us = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = locativeRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

           /* us.setDateTransient(df.format(us.getDate()));
            us.setTypeTransient(us.getTypeLocative().getName());
            us.setDate(us.getDate());
            us.setDateTransient(df.format(us.getDate()));
            us.setDevisTransient(us.getDevis().getName());
            us.setBienTransient(us.getBien().getDesignation());*/
            list.add(us);
        }
        return list;
    }
}
