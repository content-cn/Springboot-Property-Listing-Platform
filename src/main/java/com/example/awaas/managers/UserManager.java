package com.example.awaas.managers;

import com.example.awaas.dtos.UserDTO;
import com.example.awaas.entities.UserEntity;
import com.example.awaas.mappers.UserMapper;
import com.example.awaas.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserManager {
    @Autowired
    private UserRepo userRepo;

    public UserDTO getByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepo.findByEmail(email);
        if (userEntityOptional.isPresent()) {
            return UserMapper.INSTANCE.toUserDTO(userEntityOptional.get());
        }
        return null;
    }

    public void save(UserDTO userDTO) {
        userRepo.save(UserMapper.INSTANCE.toUserEntity(userDTO));
    }
}
