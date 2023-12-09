package com.patika.vet.api;

import com.patika.vet.business.abstracts.IAppointmentService;
import com.patika.vet.dto.request.AppointmentSaveRequest;
import com.patika.vet.dto.request.AppointmentUpdateRequest;
import com.patika.vet.dto.response.AppointmentResponse;
import com.patika.vet.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll(){
        return this.appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getById(@PathVariable("id") Long id){
        return this.appointmentService.findById(id);
    }

    @GetMapping("/doctor/{id}/{start-date}/{end-date}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findByDoctorIdAndDate(@PathVariable("id") Long id, @PathVariable("start-date")LocalDate startDate,@PathVariable("end-date")LocalDate endDate){
        return this.appointmentService.findByDoctorIdAndDate(id, startDate.atStartOfDay(), endDate.atStartOfDay());
    }

    @GetMapping("/animal/{id}/{start-date}/{end-date}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findByAnimalIdAndDate(@PathVariable("id") Long id,@PathVariable("start-date") LocalDate startDate,@PathVariable("end-date")LocalDate endDate){
        return this.appointmentService.findByAnimalIdAndDate(id,startDate.atStartOfDay(), endDate.atStartOfDay());
    }

    @PostMapping("/{date}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@PathVariable("date")LocalDate date, @RequestBody AppointmentSaveRequest appointment){
        return this.appointmentService.save(date,appointment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@PathVariable("id") Long id,@RequestBody AppointmentUpdateRequest updateRequest){
        return this.appointmentService.update(id,updateRequest);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        this.appointmentService.delete(id);
    }
}
