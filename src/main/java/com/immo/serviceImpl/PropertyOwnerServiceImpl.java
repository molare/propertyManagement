package com.immo.serviceImpl;

import com.immo.entities.PropertyOwner;
import com.immo.repositories.PropertyOwnerRepository;
import com.immo.service.PropertyOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
@Service("propertyOwnerService")
public class PropertyOwnerServiceImpl implements PropertyOwnerService {

    @Autowired
    private PropertyOwnerRepository propertyOwnerRepository;

    @Override
    public List<PropertyOwner> getAll() {
        return propertyOwnerRepository.findAll();
    }

    @Override
    public PropertyOwner add(PropertyOwner propertyOwner) {
        return propertyOwnerRepository.save(propertyOwner);
    }

    @Override
    public PropertyOwner update(PropertyOwner propertyOwner) {
        if(propertyOwner.getId()==0){
            return propertyOwnerRepository.save(propertyOwner);
        }
        return propertyOwnerRepository.saveAndFlush(propertyOwner);
    }

    @Override
    public PropertyOwner findById(int id) {
        return propertyOwnerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        propertyOwnerRepository.deleteById(id);
    }
}
