package com.immo.service;

import com.immo.entities.Locatif;
import com.immo.entities.City;
import com.immo.entities.Property;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface LocatifService {
    public List<Locatif> getAll();
    public Locatif add(Locatif locative);
    public Locatif update(Locatif locative);
    public Locatif findById(int id);
    public void delete(int id);
    public List<Locatif> findByProperty(Property property);
    public List<Locatif> findByCity(City city);
    public double garanty(int id);
    public Locatif findByContrat(int id);
    public List<Locatif> getLocativeNotInContrat();
    public int countLocative();
    public List<Locatif> export(int cpt, HttpServletRequest request);
}
