package com.example.awaas.mappers;

import com.example.awaas.dtos.UserDTO;
import com.example.awaas.entities.UserEntity;
import com.example.awaas.requests.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toUserEntity(UserDTO userDTO);
    UserDTO toUserDTO(UserEntity userEntity);

    UserDTO toUserDTO(UserRequest userRequest);
}
