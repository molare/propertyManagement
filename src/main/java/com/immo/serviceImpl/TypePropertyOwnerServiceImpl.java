package com.immo.serviceImpl;

import com.immo.entities.TypePropertyOwner;
import com.immo.repositories.TypePropertyOwnerRepository;
import com.immo.service.TypePropertyOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("typePropertyOwnerService")
public class TypePropertyOwnerServiceImpl implements TypePropertyOwnerService {
    @Autowired
    private TypePropertyOwnerRepository typePropertyOwnerRepository;

    @Override
    public List<TypePropertyOwner> getAll() {
        return typePropertyOwnerRepository.findAll();
    }

    @Override
    public TypePropertyOwner add(TypePropertyOwner typeproperty) {
        return typePropertyOwnerRepository.save(typeproperty);
    }

    @Override
    public TypePropertyOwner update(TypePropertyOwner typeproperty) {
        if(typeproperty.getId() ==0){
            return typePropertyOwnerRepository.save(typeproperty);
        }
        return typePropertyOwnerRepository.saveAndFlush(typeproperty);
    }

    @Override
    public TypePropertyOwner findById(int id) {
        return typePropertyOwnerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        typePropertyOwnerRepository.deleteById(id);
    }
}
