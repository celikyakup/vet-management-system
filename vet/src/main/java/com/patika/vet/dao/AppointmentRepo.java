package com.patika.vet.dao;

import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Appointment;
import com.patika.vet.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    Optional<Appointment> findByAppointmentDateAndDoctor(LocalDateTime appointmentDate, Doctor doctor);
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId,LocalDateTime startDate,LocalDateTime endDate);

}
