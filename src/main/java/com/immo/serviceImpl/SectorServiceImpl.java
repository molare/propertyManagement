package com.immo.serviceImpl;

import com.immo.entities.Sector;
import com.immo.repositories.SectorRepository;
import com.immo.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("sectorService")
public class SectorServiceImpl implements SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public List<Sector> getAll() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector add(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Override
    public Sector update(Sector sector) {
        if(sector.getId() ==0){
            return sectorRepository.save(sector);
        }
        return sectorRepository.saveAndFlush(sector);
    }

    @Override
    public Sector findById(int id) {
        return sectorRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        sectorRepository.deleteById(id);
    }
}
