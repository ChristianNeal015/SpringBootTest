package com.springboottest.SpringBootTest.service;

import com.springboottest.SpringBootTest.exceptions.ResourceNotFoundException;
import com.springboottest.SpringBootTest.models.Customer;
import com.springboottest.SpringBootTest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
      return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer, Long customerId) {
        verifyCustomerId(customerId, "Customer has been updated");
        customer.setId(customerId);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public void verifyCustomerId(Long customerId, String message) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(!customer.isPresent())
            throw new ResourceNotFoundException(message);
    }

}
