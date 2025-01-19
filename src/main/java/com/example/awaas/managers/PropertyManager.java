package com.example.awaas.managers;

import com.example.awaas.dtos.PropertyDTO;
import com.example.awaas.dtos.UserDTO;
import com.example.awaas.entities.Property;
import com.example.awaas.mappers.PropertyMapper;
import com.example.awaas.repos.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyManager {
    @Autowired
    private PropertyRepo propertyRepository;

    @Autowired
    private UserManager userManager;

    public PropertyDTO save(PropertyDTO propertyDTO) {
        Property entity = PropertyMapper.INSTANCE.toEntity(propertyDTO);

        return PropertyMapper.INSTANCE.toPropertyDTO(propertyRepository.save(entity));
    }

}
