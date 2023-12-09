package com.patika.vet.dao;

import com.patika.vet.dto.response.CustomerResponse;
import com.patika.vet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    List<Customer> findByName(String name);
    Optional<Customer> findByNameAndPhoneAndMail(String name,String phone, String mail);


}
