package com.patika.vet.dao;

import com.patika.vet.entity.AvailableDate;
import com.patika.vet.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {
    Optional<AvailableDate> findByAvailableDateAndDoctor(LocalDate availableDate, Doctor doctor);

    Optional <AvailableDate> findByAvailableDateAndDoctorId(LocalDate date,Long id);
}
