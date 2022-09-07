package com.shoppingapp.userservice.controller;

import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;
import com.shoppingapp.userservice.model.Userdata;
import com.shoppingapp.userservice.service.AdminServices;
import com.shoppingapp.userservice.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1.0/Shopping")
@Slf4j


public class UserServiceController {
    @Autowired
    private UserServices userService;
    @Autowired
    private AdminServices adminService;
//     @Operation(summary = "Registering Users",description = "A Post request for Registering User",tags = {"User Service Api"})
//         @ApiResponses(value= {
//            @ApiResponse(responseCode = "201",description = "Successfully added the user"),
//            @ApiResponse(responseCode = "400",description = "Input Validation Failed"),
//            @ApiResponse(responseCode = "500",description = "Some Exception Occured")
//    })
    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody Userdata user){

        log.info("In User Service - Registering User");
        log.debug("registering user {}",user);
        return userService.register(user);
    }
//    @Operation(summary = "Log in Users",description = "A Post request for Log in User",tags = {"User Service Api"})
//    @ApiResponses(value= {
//            @ApiResponse(responseCode = "200",description = "Successfully logged in the user"),
//            @ApiResponse(responseCode = "403",description = "Un-Successfully logged in the user"),
//            @ApiResponse(responseCode = "400",description = "Input Validation Failed"),
//            @ApiResponse(responseCode = "500",description = "Some Exception Occured")
//    })
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDetails userlogincredentials) {
        log.info("inside user service to login");
        log.debug("Login user name: {}",userlogincredentials.getUsername());
        return userService.login(userlogincredentials);
    }
//  @Operation(summary = "Log in Users",description = "A Post request for Log in User",tags = {"User Service Api"})
//  @ApiResponses(value= {
//          @ApiResponse(responseCode = "200",description = "Successfully logged in the user"),
//          @ApiResponse(responseCode = "403",description = "Un-Successfully logged in the user"),
//          @ApiResponse(responseCode = "400",description = "Input Validation Failed"),
//          @ApiResponse(responseCode = "500",description = "Some Exception Occured")
//  })
  @PostMapping(value = "/adminlogin")
  public ResponseEntity<AuthResponse> adminlogin(@Valid @RequestBody LoginDetails adminlogincredentials) {
      log.info("inside user service to login");
      log.debug("Login user name: {}",adminlogincredentials.getUsername());
      return adminService.adminlogin(adminlogincredentials);
  }

//    @Operation(summary = "Forgot password",description = "A Put request for Forgot Password",tags = {"User Service Api"})
//    @ApiResponses(value= {
//        @ApiResponse(responseCode = "200",description = "Successfully reset password"),
//        @ApiResponse(responseCode = "400",description = "Input Validation Failed"),
//        @ApiResponse(responseCode = "500",description = "Some Exception Occured")
//})
    @PutMapping(value="/{username}/forgot")
    public ResponseEntity<Object> forgotPassword(@PathVariable String username, @Valid @RequestBody LoginDetails data){
        log.info("inside user service to change forgot password {}",data.getUsername());
        return userService.forgotPassword(data);
}
//    @Operation(summary = "Validating Users",description = "A Get request for Validating User",tags = {"User Service Api"})
//	@ApiResponses(value= {
//			@ApiResponse(responseCode = "200",description = "Validated the user"),
//			@ApiResponse(responseCode = "500",description = "Some Exception Occured")
//	})
	@GetMapping(value = "/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization")final String token) {
		log.info("inside user service to validate the token");
		return userService.validate(token);
	}
//  @Operation(summary = "Validating Users",description = "A Get request for Validating User",tags = {"User Service Api"})
//	@ApiResponses(value= {
//			@ApiResponse(responseCode = "200",description = "Validated the user"),
//			@ApiResponse(responseCode = "500",description = "Some Exception Occured")
//	})
	@GetMapping(value = "/adminvalidate")
	public ResponseEntity<AuthResponse> getAdminValidity(@RequestHeader("Authorization")final String token) {
		log.info("inside admin service to validate the token");
		return adminService.adminvalidate(token);
	}
}
