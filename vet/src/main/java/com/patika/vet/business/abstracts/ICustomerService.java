package com.patika.vet.business.abstracts;

import com.patika.vet.dto.request.CustomerRequest;
import com.patika.vet.dto.response.CustomerResponse;
import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Customer;

import java.util.List;

public interface ICustomerService {

    CustomerResponse getById(Long id);

    CustomerResponse save(CustomerRequest customerRequest);
    CustomerResponse update(Long id,CustomerRequest customerRequest);
    boolean delete(Long id);

    public List<CustomerResponse> findByName(String customerName);

    public List<CustomerResponse> findAll();
}
