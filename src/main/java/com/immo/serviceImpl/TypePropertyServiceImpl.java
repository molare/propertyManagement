package com.immo.serviceImpl;


import com.immo.entities.TypeProperty;
import com.immo.repositories.TypePropertyRepository;
import com.immo.service.TypePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("typePropertyService")
public class TypePropertyServiceImpl implements TypePropertyService {
    @Autowired
    private TypePropertyRepository typeBienRepository;

    @Override
    public List<TypeProperty> getAll() {
        return typeBienRepository.findAll();
    }

    @Override
    public TypeProperty add(TypeProperty typeBien) {
        return typeBienRepository.save(typeBien);
    }

    @Override
    public TypeProperty update(TypeProperty typeBien) {
        if(typeBien.getId() ==0){
            return typeBienRepository.save(typeBien);
        }
        return typeBienRepository.saveAndFlush(typeBien);
    }

    @Override
    public TypeProperty findById(int id) {
        return typeBienRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        typeBienRepository.deleteById(id);
    }
}
