package com.immo.serviceImpl;


import com.immo.entities.City;
import com.immo.entities.Property;
import com.immo.entities.PropertyOwner;
import com.immo.entities.Sector;
import com.immo.repositories.PropertyRepository;
import com.immo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property add(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property update(Property property) {
        if(property.getId() ==0){
            return propertyRepository.save(property);
        }
        return propertyRepository.saveAndFlush(property);
    }

    @Override
    public Property findById(int id) {
        return propertyRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<Property> findByPropertyOwner(PropertyOwner propertyOwner) {
        return propertyRepository.findByPropertyOwner(propertyOwner);
    }

    @Override
    public List<Property> findBySector(Sector sector) {
        return null;
    }

    @Override
    public int countProperty() {
        String sql="SELECT COUNT(DISTINCT b.id) AS nb FROM property b";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }

    }

    @Override
    public List<Property> export(int cpt, HttpServletRequest request) {
        List<Property> list = new ArrayList<Property>();
        Property us = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = propertyRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));
            /*us.setTypeTransient(us.getTypeProperty().getName());
            us.setPropertyTransient(us.getProperty().getFirstName()+" "+us.getProperty().getLastName());
            us.setCityTransient(us.getCity().getName());
            us.setDateTransient(df.format(us.getDate()));*/
            list.add(us);
        }
        return list;
    }
}
