package com.example.awaas.response;

public class LoginResponse extends BaseResponse {
    private String token;

    public LoginResponse(int statusCode, String message, String token) {
        super(statusCode, message); // Call the parent class constructor
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
