package com.immo.service;

import com.immo.entities.TypeProperty;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypePropertyService {
    public List<TypeProperty> getAll();
    public TypeProperty add(TypeProperty typeBien);
    public TypeProperty update(TypeProperty typeBien);
    public TypeProperty findById(int id);
    public void delete(int id);
}
