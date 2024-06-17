package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> listCustomer();

    Customer findById(Long id);

    void delete(Long id);

    void save(Customer customer);


}
