package com.example.awaas.enums;

public enum PropertyTypeEnum {
    SELL,
    RENTAL,
    HOUSE,   // Add HOUSE to the enum
    APARTMENT,
    VILLA;  // Other possible property types

    // Optionally, you can add logic to map custom strings to enum values
    public static PropertyTypeEnum fromString(String type) {
        switch (type.toUpperCase()) {
            case "HOUSE":
                return HOUSE;
            case "APARTMENT":
                return APARTMENT;
            case "VILLA":
                return VILLA;
            case "SELL":
                return SELL;
            case "RENTAL":
                return RENTAL;
            default:
                throw new IllegalArgumentException("Unknown property type: " + type);
        }
    }
}
