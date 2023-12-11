package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.IVaccineService;
import com.patika.vet.dao.VaccineRepo;
import com.patika.vet.entity.Vaccine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
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
        Optional<Vaccine> isVaccineExist=this.vaccineRepo.findByNameAndCodeAndProtectionFinishDateAfterAndAnimal(vaccine.getName(), vaccine.getCode(),vaccine.getProtectionStartDate(),vaccine.getAnimal());

        if (vaccine.getProtectionStartDate().isAfter(vaccine.getProtectionFinishDate())){
            throw new RuntimeException("Başlangıç tarihi bitiş tarihinden ileride bir tarih olamaz!!");
        }

        if (isVaccineExist.isPresent()){
            throw new RuntimeException("Bu aşının daha önce kaydı yapılmıştır !!");
        }

        return this.vaccineRepo.save(vaccine);
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
