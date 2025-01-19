package com.example.awaas.repos;

import com.example.awaas.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property, Long> {
}
