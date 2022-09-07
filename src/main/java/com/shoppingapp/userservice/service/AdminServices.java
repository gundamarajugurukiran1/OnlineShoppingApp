package com.shoppingapp.userservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;

@Service
public interface AdminServices {
    public ResponseEntity<AuthResponse> adminlogin(LoginDetails loginDetails);
    public ResponseEntity<AuthResponse> adminvalidate(String authToken);
}
