package com.immo.repositories;


import com.immo.entities.Property;
import com.immo.entities.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    public Property findById(int id);
    public List<Property> findByPropertyOwner(PropertyOwner propertyOwner);
}
