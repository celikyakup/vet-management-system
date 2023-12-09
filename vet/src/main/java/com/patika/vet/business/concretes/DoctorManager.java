package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.IDoctorService;
import com.patika.vet.dao.DoctorRepo;
import com.patika.vet.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Override
    public Doctor findById(Long id) {
        return this.doctorRepo.findById(id).orElseThrow(()->new RuntimeException(id+" id'li doctor bulunamadı !!"));
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    @Override
    public Doctor save(Doctor doctor) {
        Optional<Doctor> isDoctorExist=this.doctorRepo.findByNameAndMailAndAddress(doctor.getName(), doctor.getMail(), doctor.getAddress());

        if (isDoctorExist.isEmpty()){
            return this.doctorRepo.save(doctor);
        }
        throw new RuntimeException("Bu doctor daha önce sisteme kayıt olmuştur !!");
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        Optional<Doctor> doctorFromDb=this.doctorRepo.findById(id);
        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("Güncelleme yapmak istediğiniz doktor sistemde bulunamadı !!");
        }
        doctor.setId(id);
        return this.doctorRepo.save(doctor);
    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doctorFromDb=this.doctorRepo.findById(id);
        if (doctorFromDb.isPresent()){
            this.doctorRepo.delete(doctorFromDb.get());
        }else {
            throw new RuntimeException(id+" id'li doktor sistemde kayıtlı değil !!");
        }
    }
}