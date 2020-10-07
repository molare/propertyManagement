package com.immo.repositories;

import com.immo.entities.Contract;
import com.immo.entities.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface PayRollRepository extends JpaRepository<PayRoll, Integer>{
    public PayRoll findById(int id);
    public List<PayRoll> findByContrat(Contract contrat);
}
