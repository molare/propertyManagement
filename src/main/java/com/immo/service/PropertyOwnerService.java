package com.immo.service;

import com.immo.entities.Property;
import com.immo.entities.PropertyOwner;

import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
public interface PropertyOwnerService {
    public List<PropertyOwner> getAll();
    public PropertyOwner add(PropertyOwner propertyOwner);
    public PropertyOwner update(PropertyOwner propertyOwner);
    public PropertyOwner findById(int id);
    public void delete(int id);
}
