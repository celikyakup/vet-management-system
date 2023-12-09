package com.patika.vet.mapper;

import com.patika.vet.dto.request.AppointmentSaveRequest;
import com.patika.vet.dto.request.AppointmentUpdateRequest;
import com.patika.vet.dto.response.AppointmentResponse;
import com.patika.vet.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AppointmentMapper {
    @Mapping(source = "appointmentDate",target = "appointmentDate",dateFormat = "yyyy-MM-dd HH:mm")
    Appointment asEntity(AppointmentSaveRequest saveRequest);

    @Mapping(source = "appointmentDate",target = "appointmentDate",dateFormat = "yyyy-MM-dd HH:mm")
    Appointment asEntity(AppointmentUpdateRequest updateRequest);

    @Mapping(source = "appointmentDate",target = "appointmentDate",dateFormat = "yyyy-MM-dd HH:mm")
    AppointmentResponse asOutput(Appointment appointment);


    @Mapping(source = "appointmentDate",target = "appointmentDate",dateFormat = "yyyy-MM-dd HH:mm")
    List<AppointmentResponse> asOutput(List<Appointment> appointment);

    @Mapping(source = "appointmentDate",target = "appointmentDate",dateFormat = "yyyy-MM-dd HH:mm")
    void update(@MappingTarget Appointment entity,AppointmentUpdateRequest updateRequest);
}
