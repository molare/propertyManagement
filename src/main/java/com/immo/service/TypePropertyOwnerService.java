package com.immo.service;

import com.immo.entities.TypePropertyOwner;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypePropertyOwnerService {
    public List<TypePropertyOwner> getAll();
    public TypePropertyOwner add(TypePropertyOwner typePropertyOwner);
    public TypePropertyOwner update(TypePropertyOwner typePropertyOwner);
    public TypePropertyOwner findById(int id);
    public void delete(int id);
}
