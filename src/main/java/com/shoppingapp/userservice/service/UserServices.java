package com.shoppingapp.userservice.service;


import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;
import com.shoppingapp.userservice.model.Userdata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    public ResponseEntity<AuthResponse> login(LoginDetails loginDetails);
    public ResponseEntity<Object> register(Userdata user);
    public ResponseEntity<Object> forgotPassword(LoginDetails data);
    public ResponseEntity<AuthResponse> validate(String authToken);


}
