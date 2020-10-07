package com.immo.serviceImpl;


import com.immo.entities.TypeLocatif;
import com.immo.repositories.TypeLocatifRepository;
import com.immo.service.TypeLocatifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("typeLocativeService")
public class TypeLocatifServiceImpl implements TypeLocatifService {
    @Autowired
    private TypeLocatifRepository typeLocativeRepository;

    @Override
    public List<TypeLocatif> getAll() {
        return typeLocativeRepository.findAll();
    }

    @Override
    public TypeLocatif add(TypeLocatif typeLocative) {
        return typeLocativeRepository.save(typeLocative);
    }

    @Override
    public TypeLocatif update(TypeLocatif typeLocative) {
        if(typeLocative.getId() ==0){
            return typeLocativeRepository.save(typeLocative);
        }
        return typeLocativeRepository.saveAndFlush(typeLocative);
    }

    @Override
    public TypeLocatif findById(int id) {
        return typeLocativeRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        typeLocativeRepository.deleteById(id);
    }
}
