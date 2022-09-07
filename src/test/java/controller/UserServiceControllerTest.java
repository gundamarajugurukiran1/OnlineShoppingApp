package controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.shoppingapp.userservice.controller.UserServiceController;
import com.shoppingapp.userservice.model.Admindata;
import com.shoppingapp.userservice.model.AuthResponse;
import com.shoppingapp.userservice.model.LoginDetails;
import com.shoppingapp.userservice.model.Userdata;
import com.shoppingapp.userservice.service.AdminServices;
import com.shoppingapp.userservice.service.UserServices;

@ExtendWith(MockitoExtension.class)
public class UserServiceControllerTest {

	@Mock
	UserServices userServices;
	@Mock
	AdminServices adminServices;
	
	@InjectMocks
	UserServiceController userController;
	
	UserDetails userdetails;
	Userdata userData;
	Admindata adminData;
	Optional<Userdata> user;
	LoginDetails data;
	
	@Test
	void register() {
		
		userData=new Userdata("kumar", "P R", "kumar", "kumar", 0);
		when(userServices.register(userData)).thenReturn(new ResponseEntity<Object>("Added Successfully",HttpStatus.CREATED));
		assertEquals(201, userController.register(userData).getStatusCodeValue());
	}
	@Test
	void login() {
		data=new LoginDetails("kumar","kumar1");
		when(userServices.login(data)).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.login(data).getStatusCodeValue());
	}
	@Test
	void forgotPassword() {
		data=new LoginDetails("kumar","kumar1");
		when(userServices.forgotPassword(data)).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.forgotPassword("kumar", data).getStatusCodeValue());
	}
	@Test
	void validate() {
		when(userServices.validate("token")).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.getValidity("token").getStatusCodeValue());
	}
	@Test
	void adminlogin() {
		data=new LoginDetails("kumar","kumar1");
		when(adminServices.adminlogin(data)).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.adminlogin(data).getStatusCodeValue());
	}
	@Test
	void adminvalidate() {
		when(adminServices.adminvalidate("token")).thenReturn(new ResponseEntity<>(new AuthResponse(),HttpStatus.OK));
		assertEquals(200, userController.getAdminValidity("token").getStatusCodeValue());
	}
	
	
	
	
	
}
