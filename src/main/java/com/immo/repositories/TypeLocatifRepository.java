package com.immo.repositories;


import com.immo.entities.TypeLocatif;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypeLocatifRepository extends JpaRepository<TypeLocatif, Integer> {
    public TypeLocatif findById(int id);
}
