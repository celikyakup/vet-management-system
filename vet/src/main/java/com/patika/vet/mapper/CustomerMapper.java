package com.patika.vet.mapper;

import com.patika.vet.dto.request.CustomerRequest;
import com.patika.vet.dto.response.CustomerResponse;
import com.patika.vet.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer asEntity(CustomerRequest customerRequest);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customer);

    void update(@MappingTarget Customer entity,CustomerRequest customerRequest);
}
