package com.immo.service;

import com.immo.entities.TypeLocatif;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypeLocatifService {
    public List<TypeLocatif> getAll();
    public TypeLocatif add(TypeLocatif typeLocative);
    public TypeLocatif update(TypeLocatif typeLocative);
    public TypeLocatif findById(int id);
    public void delete(int id);
}
