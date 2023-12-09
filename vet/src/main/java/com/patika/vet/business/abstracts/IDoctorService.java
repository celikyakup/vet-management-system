package com.patika.vet.business.abstracts;

import com.patika.vet.entity.Doctor;

import javax.print.Doc;
import java.util.List;

public interface IDoctorService {
    Doctor findById(Long id);

    List<Doctor> findAll();

    Doctor save(Doctor doctor);

    Doctor update(Long id,Doctor doctor);
    void delete(Long id);
}
