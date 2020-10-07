package com.immo.repositories;

import com.immo.entities.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 09/10/2019.
 */
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner,Integer> {
    public PropertyOwner findById(int id);
}
