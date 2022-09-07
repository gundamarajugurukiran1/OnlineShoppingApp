package com.shoppingapp.userservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {
    private String username;
    private Boolean valid;
    private String token;
    private String fname;
    private String lname;
	
public AuthResponse(String username, Boolean valid, String token) {
//		super();
		this.username = username;
		this.valid = valid;
		this.token = token;
	}

}
