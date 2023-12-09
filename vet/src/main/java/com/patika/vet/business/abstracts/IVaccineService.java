package com.patika.vet.business.abstracts;

import com.patika.vet.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine getById(Long id);
    List<Vaccine> findAll();
    Vaccine save(Vaccine vaccine);

    Vaccine update(Long id,Vaccine vaccine);
    boolean delete(Long id);

    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByProtectionDate(LocalDate startDate,LocalDate endDate);
}
