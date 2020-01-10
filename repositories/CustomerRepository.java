package com.springboottest.SpringBootTest.repositories;

import com.springboottest.SpringBootTest.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
