package com.example.awaas.managers;

import com.example.awaas.dtos.PropertyDTO;
import com.example.awaas.dtos.UserDTO;
import com.example.awaas.entities.Property;
import com.example.awaas.enums.PropertyTypeEnum;
import com.example.awaas.mappers.PropertyMapper;
import com.example.awaas.repos.PropertyRepo;
import com.example.awaas.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public PropertyDTO findById(Long id) {
        Optional<Property> entities = propertyRepository.findById(id);
        if (entities.isPresent()) {
            return PropertyMapper.INSTANCE.toPropertyDTO(entities.get());
        }
        return null;
    }

    public Page<PropertyResponse> getAllProperties(String location, Double minPrice, Double maxPrice, PropertyTypeEnum type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyRepository.findAllWithFilters(location, minPrice, maxPrice, type, pageable);

        // Map the Page<Property> to Page<PropertyResponse>
        return PropertyMapper.INSTANCE.toPagePropertyResponse(propertyPage);
    }

}
