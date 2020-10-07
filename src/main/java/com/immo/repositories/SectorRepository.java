package com.immo.repositories;

import com.immo.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface SectorRepository extends JpaRepository<Sector, Integer>{
    public Sector findById(int id);
}
