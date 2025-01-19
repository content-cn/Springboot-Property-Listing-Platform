package com.example.awaas.mappers;

import com.example.awaas.dtos.PropertyDTO;
import com.example.awaas.entities.Property;
import com.example.awaas.requests.CreatePropertyRequest;
import com.example.awaas.response.PropertyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property toEntity(PropertyDTO dto);
    PropertyDTO toPropertyDTO(Property property);
    PropertyDTO toPropertyDTO(CreatePropertyRequest createPropertyRequest);
    PropertyResponse toPropertyResponse(PropertyDTO propertyDTO);
}
