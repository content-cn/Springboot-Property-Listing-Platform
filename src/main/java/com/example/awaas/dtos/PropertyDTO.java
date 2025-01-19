package com.example.awaas.dtos;

import com.example.awaas.enums.PropertyTypeEnum;

public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String location;
    private PropertyTypeEnum type; // e.g., "RENT" or "SELL"
    private String imageUrl; // URL of the uploaded property image
    private UserDTO owner; // Email of the property owner

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PropertyTypeEnum getType() {
        return type;
    }

    public void setType(PropertyTypeEnum type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public PropertyDTO() {

    }

    public PropertyDTO(Long id, String title, String description, Double price, String location, PropertyTypeEnum type, String imageUrl, String ownerEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.type = type;
        this.imageUrl = imageUrl;
    }
}
