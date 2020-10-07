package com.immo.service;

import com.immo.entities.Property;
import com.immo.entities.PropertyOwner;
import com.immo.entities.Sector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface PropertyService {
    public List<Property> getAll();
    public Property add(Property property);
    public Property update(Property property);
    public Property findById(int id);
    public void delete(int id);
    public List<Property> findByPropertyOwner(PropertyOwner propertyOwner);
    public List<Property> findBySector(Sector sector);
    public int countProperty();
    public List<Property> export(int cpt, HttpServletRequest request);
}
