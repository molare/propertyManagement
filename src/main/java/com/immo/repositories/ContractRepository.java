package com.immo.repositories;

import com.immo.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 29/10/2019.
 */
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    public Contract findById(int id);
}
