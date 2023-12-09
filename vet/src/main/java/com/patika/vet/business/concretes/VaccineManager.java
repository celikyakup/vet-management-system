package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.IVaccineService;
import com.patika.vet.dao.VaccineRepo;
import com.patika.vet.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineManager implements IVaccineService {

    @Autowired
    private VaccineRepo vaccineRepo;
    @Override
    public Vaccine getById(Long id) {
        return vaccineRepo.findById(id).orElseThrow(()->new RuntimeException(id + " 'li aşı bulunamadı !!"));
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        Optional<Vaccine> isVaccineExist=this.vaccineRepo.findByNameAndCodeAndProtectionStartDateBeforeAndAnimal(vaccine.getName(), vaccine.getCode(),vaccine.getProtectionFinishDate(),vaccine.getAnimal());

        if (isVaccineExist.isEmpty()){
            return this.vaccineRepo.save(vaccine);
        }
        throw new RuntimeException("Bu aşının daha önce kaydı yapılmıştır !!");
    }

    @Override
    public Vaccine update(Long id,Vaccine vaccine) {
        Optional<Vaccine> vaccineFromDb=this.vaccineRepo.findById(id);
        if (vaccineFromDb.isPresent()){
            vaccine.setId(id);
            return this.vaccineRepo.save(vaccine);
        }
        throw new RuntimeException("Güncelleme yapmak istediğiniz aşı sisteme kayıt edilmemiş !!");
    }

    @Override
    public boolean delete(Long id) {

        Optional<Vaccine> vaccineFromDb=this.vaccineRepo.findById(id);
        if (vaccineFromDb.isPresent()){
            this.vaccineRepo.delete(vaccineFromDb.get());
            return true;
        }else {
            throw new RuntimeException(id+" id'li aşı sisteme kayıt edilmemiş !!");
        }
    }

    @Override
    public List<Vaccine> findByAnimalId(Long animalId) {
        return this.vaccineRepo.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> findByProtectionDate(LocalDate startDate, LocalDate endDate) {
        return this.vaccineRepo.findByProtectionFinishDateBetween(startDate,endDate);
    }
}
