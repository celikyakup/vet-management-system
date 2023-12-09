package com.patika.vet.dto.request;

import com.patika.vet.entity.Animal;
import com.patika.vet.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentUpdateRequest {
    private String appointmentDate;
    private Doctor doctor;
    private Animal animal;
}
