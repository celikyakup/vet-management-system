package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.IAvailableDateService;
import com.patika.vet.core.exception.MethodArgumentNotValidException;
import com.patika.vet.core.exception.NotFoundException;
import com.patika.vet.dao.AvailableDateRepo;
import com.patika.vet.dao.DoctorRepo;
import com.patika.vet.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo dateRepo;

    @Override
    public List<AvailableDate> findAll() {
        return this.dateRepo.findAll();
    }

    @Override
    public AvailableDate getById(Long id) {
        return this.dateRepo.findById(id).orElseThrow(()->new NotFoundException(id+ " id'li bir kayıt sistemde yok !!"));
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> isDateExist=this.dateRepo.findByAvailableDateAndDoctor(availableDate.getAvailableDate(),availableDate.getDoctor());
        if (isDateExist.isEmpty()){
            return this.dateRepo.save(availableDate);
        }
        throw new MethodArgumentNotValidException("Bu doktora bu tarih daha önce kayıt edilmiş!!");
    }

    @Override
    public AvailableDate update(Long id, AvailableDate availableDate) {
        Optional<AvailableDate> dateFromDb=this.dateRepo.findById(id);
        if (dateFromDb.isEmpty()){
            throw new NotFoundException("Güncelleme yapmak istediğiniz "+id+" li tarih sistemde kayıtlı değildir !!");
        }
        availableDate.setId(id);
        return this.dateRepo.save(availableDate);
    }

    @Override
    public void delete(Long id) {
        Optional<AvailableDate> dateFromDb=this.dateRepo.findById(id);
        if (dateFromDb.isPresent()){
            this.dateRepo.delete(dateFromDb.get());
        }else {
            throw new NotFoundException(id+" id'li date sistemde kayıtlı değil!!");
        }
    }
    @Override
    public Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate date, Long id) {
        return this.dateRepo.findByAvailableDateAndDoctorId(date,id);
    }
}
