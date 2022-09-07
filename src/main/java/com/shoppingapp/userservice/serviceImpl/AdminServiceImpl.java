package com.shoppingapp.userservice.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shoppingapp.userservice.model.Admindata;
import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;

import com.shoppingapp.userservice.repository.AdminRepository;

import com.shoppingapp.userservice.service.AdminServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminServices {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<AuthResponse> adminlogin(LoginDetails loginDetails) {
        log.info("inside admin service implementation to login");
        final UserDetails admindetails = customerDetailsService.loadAdminByUsername(loginDetails.getUsername());
        String uid = "";
        String generateToken = "";
        Optional<Admindata> admin=adminRepository.findById(admindetails.getUsername());
        if (admin.isPresent() && admindetails.getPassword().equals(loginDetails.getPassword())) {
            uid = loginDetails.getUsername();
            generateToken = jwtUtil.generateToken(admindetails);
            log.info("login successful");
            return new ResponseEntity<>(new AuthResponse(uid,true,generateToken),HttpStatus.OK);
        } else {
            log.info("At Login : ");
            log.error("Not Accessible");
            return new ResponseEntity<>(new AuthResponse(loginDetails.getUsername(),false,"In Correct Password"), HttpStatus.FORBIDDEN);
        }
    }
    @Override
	public ResponseEntity<AuthResponse> adminvalidate(String authToken) {
    	String token1 = authToken.substring(7);
		AuthResponse res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtUtil.validateToken(token1))) {
			res.setUsername(jwtUtil.extractUsername(token1));
			res.setValid(true);
			Optional<Admindata> admin=adminRepository.findById(jwtUtil.extractUsername(token1));
			if(admin.isPresent()) {
				res.setUsername(admin.get().getUsername());
				res.setValid(true);
				res.setToken("token successfully validated");
				log.info("token successfully validated");
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		} else {
			res.setValid(false);
			res.setToken("Invalid Token Received");
			log.info("At Validity : ");
			log.error("Token Has Expired");
			return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
	}
	}

