package com.immo.serviceImpl;

import com.immo.entities.Customer;
import com.immo.repositories.CustomerRepository;
import com.immo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
@Service("locaterService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer add(Customer Customer) {
        return customerRepository.save(Customer);
    }

    @Override
    public Customer update(Customer Customer) {
        if(Customer.getId()==0){
            return customerRepository.save(Customer);
        }
        return customerRepository.saveAndFlush(Customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public int countLocater() {
        String sql="SELECT COUNT(DISTINCT l.id) AS nb FROM Customer l";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }
    }

    @Override
    public List<Customer> export(int cpt, HttpServletRequest request) {
        List<Customer> list = new ArrayList<Customer>();
        Customer us = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = customerRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

          //  us.setDateTransient(df.format(us.getDate()));
            list.add(us);
        }
        return list;
    }
}
