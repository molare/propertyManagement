package com.immo.repositories;

import com.immo.entities.TypePropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypePropertyOwnerRepository extends JpaRepository<TypePropertyOwner, Integer>{
    public TypePropertyOwner findById(int id);
}
