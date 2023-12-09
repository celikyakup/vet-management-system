package com.patika.vet.business.abstracts;


import com.patika.vet.entity.AvailableDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAvailableDateService {

    public List<AvailableDate> findAll();
    public AvailableDate getById(Long id);
    public AvailableDate save(AvailableDate availableDate);
    public AvailableDate update(Long id, AvailableDate availableDate);
    public void delete(Long id);

    public Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate date,Long id);

}
