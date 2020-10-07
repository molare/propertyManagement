package com.immo.repositories;

import com.immo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 09/10/2019.
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findById(int id);
}
