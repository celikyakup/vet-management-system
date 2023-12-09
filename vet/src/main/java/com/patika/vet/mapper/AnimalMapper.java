package com.patika.vet.mapper;

import com.patika.vet.dto.request.AnimalSaveRequest;
import com.patika.vet.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper
public interface AnimalMapper {

    Animal asEntity(AnimalSaveRequest animalSaveRequest);

}
