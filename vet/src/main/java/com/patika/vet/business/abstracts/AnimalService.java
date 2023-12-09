package com.patika.vet.business.abstracts;

import com.patika.vet.dto.request.AnimalSaveRequest;
import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Customer;

import java.util.List;

public interface AnimalService {

    Animal getById(Long id);

    Animal save(Animal animal);
    Animal update(Long id,Animal animal);
    boolean delete(Long id);

    public List<Animal> findAllByCustomerName(String customerName);

    public List<Animal> findAll();
    public List<Animal> findByName(String name);
}
