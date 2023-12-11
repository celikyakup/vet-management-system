package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.AnimalService;
import com.patika.vet.dao.AnimalRepo;
import com.patika.vet.entity.Animal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {

    private final AnimalRepo animalRepo;
    @Override
    public Animal getById(Long id) {
        return animalRepo.findById(id).orElseThrow(()->new RuntimeException(id + "id' li Hayvan Bulunamadı !!"));
    }

    @Override
    public Animal save(Animal animal) {
        Optional<Animal> isAnimalExist=this.animalRepo.findByNameAndCustomer(animal.getName(), animal.getCustomer());
        if (isAnimalExist.isEmpty()){
            return this.animalRepo.save(animal);
        }
        throw new RuntimeException("Bu hayvan daha önce sisteme kayıt edilmiştir !!");
    }

    @Override
    public Animal update(Long id,Animal animal) {
        Animal isAnimalExistDb=this.getById(id);
        if (isAnimalExistDb==null){
            throw new RuntimeException(id+ "Güncelleme yaptığınız hayvan sistemde bulunamadı.");
        }

        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Animal> isAnimalExistDb=animalRepo.findById(id);
        if (isAnimalExistDb.isPresent()){
            animalRepo.delete(isAnimalExistDb.get());
            return true;
        }
        else {
            throw new RuntimeException(id+ "id'li hayvan sistemde bulunamadığı için silme işlemi yapılamadı !");
        }
    }

    @Override
    public List<Animal> findAllByCustomerName(String customerName) {
        return animalRepo.findByCustomerName(customerName);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepo.findAll();
    }

    @Override
    public List<Animal> findByName(String name) {
        return animalRepo.findByName(name);
    }

}
