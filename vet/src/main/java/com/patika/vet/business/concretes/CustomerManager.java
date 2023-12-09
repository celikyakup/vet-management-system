package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.ICustomerService;
import com.patika.vet.dao.CustomerRepo;
import com.patika.vet.dto.request.CustomerRequest;
import com.patika.vet.dto.response.CustomerResponse;
import com.patika.vet.entity.Customer;
import com.patika.vet.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerManager(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse getById(Long id) {
        return this.customerMapper.asOutput(this.customerRepo.findById(id).orElseThrow(()->new RuntimeException(id+" id'li Hayvan Sahibi Bulunamadı !")));
    }

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        Optional<Customer> isCustomerExist=customerRepo.findByNameAndPhoneAndMail(customerRequest.getName(),customerRequest.getPhone(),customerRequest.getMail());
        if (isCustomerExist.isEmpty()){
            Customer customer=customerRepo.save(customerMapper.asEntity(customerRequest));
            return customerMapper.asOutput(customer);
        }
        /*Customer customer=this.customerRepo.save(this.customerMapper.asEntity(customerRequest));
        return this.customerMapper.asOutput(customer);*/
        throw new RuntimeException("Bu hayvan sahibi daha önce sisteme kayıt edilmiş !!");
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb=this.customerRepo.findById(id);
        Optional<Customer> isCustomerExist=customerRepo.findByNameAndPhoneAndMail(customerRequest.getName(),customerRequest.getPhone(),customerRequest.getMail());
        if (customerFromDb.isEmpty()){
            throw new RuntimeException(id +"Güncelleme yapmak istediğiniz hayvan sahibi sistemde kayıtlı değil !");
        }
        if (isCustomerExist.isPresent()){
            throw new RuntimeException("Bu hayvan sahibi sisteme daha önce kayıt edilmiş !!");
        }

        Customer customer=customerFromDb.get();
        this.customerMapper.update(customer,customerRequest);
        return this.customerMapper.asOutput(this.customerRepo.save(customer));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> customerFromDb=this.customerRepo.findById(id);
        if (customerFromDb.isPresent()){
            this.customerRepo.delete(customerFromDb.get());
            return true;
        }else {
            throw new RuntimeException(id+" id 'li yazar sistemde kayıtlı değil !!");

        }
    }

    @Override
    public List<CustomerResponse> findByName(String customerName) {
        return this.customerMapper.asOutput(this.customerRepo.findByName(customerName));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return this.customerMapper.asOutput(this.customerRepo.findAll());
    }
}
