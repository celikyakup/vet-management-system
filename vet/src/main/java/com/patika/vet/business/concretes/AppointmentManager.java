package com.patika.vet.business.concretes;

import com.patika.vet.business.abstracts.IAppointmentService;
import com.patika.vet.business.abstracts.IAvailableDateService;
import com.patika.vet.core.Helper;
import com.patika.vet.core.exception.NotFoundException;
import com.patika.vet.dao.AppointmentRepo;
import com.patika.vet.dao.AvailableDateRepo;
import com.patika.vet.dao.DoctorRepo;
import com.patika.vet.dto.request.AppointmentSaveRequest;
import com.patika.vet.dto.request.AppointmentUpdateRequest;
import com.patika.vet.dto.response.AppointmentResponse;
import com.patika.vet.entity.Appointment;
import com.patika.vet.entity.AvailableDate;
import com.patika.vet.entity.Doctor;
import com.patika.vet.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;


    private final AvailableDateManager availableDateService;

    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponse> findAll() {
        return this.appointmentMapper.asOutput(appointmentRepo.findAll());
    }

    @Override
    public AppointmentResponse findById(Long id) {
        return this.appointmentMapper.asOutput(appointmentRepo.findById(id).orElseThrow(()->new NotFoundException(id +" id'li randevu sistemde kayıtlı değil !!")));
    }

    @Override
    public List<AppointmentResponse> findByDoctorIdAndDate(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.appointmentMapper.asOutput(appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId,startDate,endDate));
    }

    @Override
    public List<AppointmentResponse> findByAnimalIdAndDate(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.appointmentMapper.asOutput(appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId,startDate,endDate));
    }

    @Override
    public AppointmentResponse save(LocalDate date, AppointmentSaveRequest appointmentSaveRequest) {
        Optional<AvailableDate> isAvailable=this.availableDateService.findByAvailableDateAndDoctorId(date,appointmentSaveRequest.getDoctor().getId());
        Optional<Appointment> isAppointmenExist=this.appointmentRepo.findByAppointmentDateAndDoctor(Helper.StringToDate(appointmentSaveRequest.getAppointmentDate()),appointmentSaveRequest.getDoctor());


        if (isAvailable.isEmpty()){
            throw new NotFoundException("Doktor bu tarihte çalışmamaktadır !!");
        } else if (!date.isEqual(ChronoLocalDate.from(Helper.StringToDate(appointmentSaveRequest.getAppointmentDate())))) {
            throw new NotFoundException("Randevu tarihiyle doktorun çalışma günü uyuşmuyor!!");

        } else if (isAppointmenExist.isPresent()){
            throw new NotFoundException("Girilen saatte bir randevu mevcuttur!!");
        }

        else {
            Appointment appointment=this.appointmentRepo.save(this.appointmentMapper.asEntity(appointmentSaveRequest));

            return this.appointmentMapper.asOutput(appointment);
        }
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentUpdateRequest updateRequest) {
        Optional<AvailableDate> isAvailable=this.availableDateService.findByAvailableDateAndDoctorId(LocalDate.from(Helper.StringToDate(updateRequest.getAppointmentDate())), updateRequest.getDoctor().getId());
        Optional<Appointment> isAppointmenExist=this.appointmentRepo.findByAppointmentDateAndDoctor(Helper.StringToDate(updateRequest.getAppointmentDate()),updateRequest.getDoctor());
        Optional<Appointment> appointmentFromDb=this.appointmentRepo.findById(id);
        if (appointmentFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li randevu sistemde bulunamadı.");
        }
        if (isAvailable.isEmpty()){
            throw new NotFoundException("Doktor bu tarihte çalışmamaktadır !!");
        }
         else if (isAppointmenExist.isPresent()){
            throw new NotFoundException("Girilen saatte bir randevu mevcuttur!!");
        }
        Appointment updateAppointment=appointmentFromDb.get();
        this.appointmentMapper.update(updateAppointment,updateRequest);
        this.appointmentRepo.save(updateAppointment);
        return this.appointmentMapper.asOutput(updateAppointment);
    }

    @Override
    public void delete(Long id) {
        Optional<Appointment> appointmentFromDb=this.appointmentRepo.findById(id);
        if (appointmentFromDb.isEmpty()){
            throw new NotFoundException("Randevu bulunamadığı için silme işlemi yapılamıyor!!");
        }
        this.appointmentRepo.delete(appointmentFromDb.get());
    }
}
