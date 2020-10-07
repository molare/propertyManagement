package com.immo.repositories;


import com.immo.entities.Locatif;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface LocatifRepository extends JpaRepository<Locatif, Integer> {
    public Locatif findById(int id);

}
