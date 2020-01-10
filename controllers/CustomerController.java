package com.springboottest.SpringBootTest.controllers;

import com.springboottest.SpringBootTest.models.Customer;
import com.springboottest.SpringBootTest.models.errors.AdvancedResponse;
import com.springboottest.SpringBootTest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(
                AdvancedResponse.create(allCustomers, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        customerService.verifyCustomerId(customerId, "error fetching customer");
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(
                AdvancedResponse.create(customer, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(
            AdvancedResponse.create(newCustomer, "Customer created", HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }
    @PutMapping("customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        customerService.verifyCustomerId(customerId, "Customer updated");
        Customer customer1 = customerService.updateCustomer(customer, customerId);
        return new ResponseEntity<>(
                AdvancedResponse.create(customer1, "Customer has been updated", HttpStatus.ACCEPTED.value()),
                            HttpStatus.ACCEPTED);
    }
    @DeleteMapping("customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        customerService.verifyCustomerId(customerId, "this id does not exist for Customers");
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
