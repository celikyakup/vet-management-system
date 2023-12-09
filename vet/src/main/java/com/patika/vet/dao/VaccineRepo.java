package com.patika.vet.dao;

import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VaccineRepo extends JpaRepository<Vaccine,Long> {

    Optional<Vaccine> findByNameAndCodeAndProtectionStartDateBeforeAndAnimal(String name, String code, LocalDate endTime, Animal animal);

    @Query("SELECT v FROM Vaccine v JOIN v.animal a WHERE a.id=:animalId")
    List<Vaccine> findByAnimalId(Long animalId);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate,LocalDate endDate);

}
