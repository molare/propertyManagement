package com.immo.service;

import com.immo.entities.Sector;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface SectorService {
    public List<Sector> getAll();
    public Sector add(Sector sector);
    public Sector update(Sector sector);
    public Sector findById(int id);
    public void delete(int id);
}
