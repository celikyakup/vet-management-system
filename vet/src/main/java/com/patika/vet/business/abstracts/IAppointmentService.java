package com.patika.vet.business.abstracts;

import com.patika.vet.dto.request.AppointmentSaveRequest;
import com.patika.vet.dto.request.AppointmentUpdateRequest;
import com.patika.vet.dto.response.AppointmentResponse;
import com.patika.vet.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    List<AppointmentResponse> findAll();
    AppointmentResponse findById(Long id);
    List<AppointmentResponse> findByDoctorIdAndDate(Long doctorId, LocalDateTime startDateTime,LocalDateTime endDate);
    List<AppointmentResponse> findByAnimalIdAndDate(Long animalId,LocalDateTime startDate,LocalDateTime endDate);

    AppointmentResponse save(LocalDate date, AppointmentSaveRequest appointmentSaveRequest);

    AppointmentResponse update(Long id, AppointmentUpdateRequest updateRequest);

    void delete(Long id);

}
