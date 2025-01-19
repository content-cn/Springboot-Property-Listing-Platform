package com.example.awaas.requests;

import com.example.awaas.enums.PropertyTypeEnum;

public class CreatePropertyRequest {
    private String title;
    private String description;
    private Double price;
    private String location;
    private PropertyTypeEnum type;

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

    public CreatePropertyRequest(String title, String description, Double price, String location, PropertyTypeEnum type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.type = type;
    }
}
