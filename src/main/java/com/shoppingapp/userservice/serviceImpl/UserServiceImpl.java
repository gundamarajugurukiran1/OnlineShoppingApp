package com.shoppingapp.userservice.serviceImpl;

import com.shoppingapp.userservice.Exception.UserExistsException;
import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;
import com.shoppingapp.userservice.model.Userdata;
import com.shoppingapp.userservice.repository.UserRepository;
import com.shoppingapp.userservice.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl  implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<AuthResponse> login(LoginDetails loginDetails) {
        log.info("inside user service implementation to login");
        final UserDetails userdetails = customerDetailsService.loadUserByUsername(loginDetails.getUsername());
        String uid = "";
        String generateToken = "";
        Optional<Userdata> user=userRepository.findById(userdetails.getUsername());
        if (user.isPresent() && userdetails.getPassword().equals(loginDetails.getPassword())) {
            uid = loginDetails.getUsername();
            generateToken = jwtUtil.generateToken(userdetails);
            log.info("login successful");
            return new ResponseEntity<>(new AuthResponse(uid,true,generateToken,user.get().getFirstname(),user.get().getLastname()), HttpStatus.OK);
        } else {
            log.info("At Login : ");
            log.error("Not Accessible");
            return new ResponseEntity<>(new AuthResponse(loginDetails.getUsername(),false,"In Correct Password",null,null), HttpStatus.FORBIDDEN);
        }
    }
    @Override
    public ResponseEntity<Object> register(Userdata userData) {
        log.info("inside user service implementation to register user");
        Optional<Userdata> user=userRepository.findById(userData.getUsername());
        if(user.isEmpty()) {
            try {
                log.info("registered successfully");
                userRepository.save(userData);
                return new ResponseEntity<>("User Added Successfully", HttpStatus.CREATED);
            }
            catch(RuntimeException ex) {
                log.info("User already exists");
                throw new UserExistsException("Login id already Exists");
            }

        }
        else {
            log.info("Username already exists");
            throw new UserExistsException("Username already Exists");
        }
    }
    @Override
    public ResponseEntity<Object> forgotPassword(LoginDetails data){
        Optional<Userdata> user = userRepository.findById(data.getUsername());
        if (user.isEmpty()) {
            log.info("user name not found");
            throw new UsernameNotFoundException("Username Not Found, Resister");
        }
        log.info("resetting the password");
        user.get().setPassword(data.getPassword());
        userRepository.save(user.get());
        return new ResponseEntity<>("Password reset successfull",HttpStatus.OK);
    }
    @Override
	public ResponseEntity<AuthResponse> validate(String authToken) {
		String token1 = authToken.substring(7);
		AuthResponse res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtUtil.validateToken(token1))) {
			res.setUsername(jwtUtil.extractUsername(token1));
			res.setValid(true);
			Optional<Userdata> user1=userRepository.findById(jwtUtil.extractUsername(token1));
			if(user1.isPresent()) {
				res.setUsername(user1.get().getUsername());
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
