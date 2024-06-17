package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements ICustomerService {

    private final static Map<Long, Customer > customersList;

    static {
        customersList = new HashMap<>();
        customersList.put(1L, new Customer(1L, "quách ngọc yến", "quachngocyen2729@gmail.com", "bạc liêu"));
        customersList.put(2L, new Customer(2L, "trần thị ngọc anh", "tranthingocanh0610@gmail.com", "nghệ an"));
        customersList.put(3L, new Customer(3L, "lâm thị thủy", "lamthithuy@gmail.com", "nghệ an"));
        customersList.put(4L, new Customer(4L, "lê thị sương", "lethisuong@gmail.com", "nghệ an"));
        customersList.put(5L, new Customer(5L, "hứa thu sương", "huathusuong@gmail.com", "lạng sơn"));
    }

    @Override
    public List<Customer> listCustomer() {
        return new ArrayList<>(customersList.values());
    }

    @Override
    public Customer findById(Long id) {
        return customersList.get(id);
    }

    @Override
    public void delete(Long id) {
        customersList.remove(id);
    }

    @Override
    public void save(Customer customer) {
        customersList.put(customer.getId(), customer);
    }
}
