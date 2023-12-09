package com.patika.vet.dao;

import com.patika.vet.entity.AvailableDate;
import com.patika.vet.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByNameAndMailAndAddress(String name,String mail,String address);

}
