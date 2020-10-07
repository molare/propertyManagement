package com.immo.service;

import com.immo.entities.Customer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
public interface CustomerService {
    public List<Customer> getAll();
    public Customer add(Customer locater);
    public Customer update(Customer locater);
    public Customer findById(int id);
    public void delete(int id);
    public int countLocater();
    public List<Customer> export(int cpt, HttpServletRequest request);

}
