package com.patika.vet.dao;

import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalRepo extends JpaRepository<Animal,Long> {
    List<Animal> findByName(String name);
    Optional<Animal> findByNameAndCustomer(String name, Customer customer);

    List<Animal> findByCustomerName(String customerName);
}
