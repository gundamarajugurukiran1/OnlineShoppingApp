package service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.shoppingapp.userservice.Exception.UnauthorizedException;
import com.shoppingapp.userservice.model.Userdata;
import com.shoppingapp.userservice.repository.AdminRepository;
import com.shoppingapp.userservice.repository.UserRepository;
import com.shoppingapp.userservice.serviceImpl.CustomerDetailsService;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	UserDetails userdetails;
	
	@Mock
	UserRepository userservice;
	@Mock
	AdminRepository adminservice;
	@InjectMocks
	CustomerDetailsService custdetailservice;

	@Test
	 void loadUserByUsernameTest() {
		
		Userdata user1=new Userdata("kumar", "P R", "kumar", "kumar", 0);
		Optional<Userdata> data =Optional.of(user1) ;
		when(userservice.findById("kumar")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("kumar");
		assertEquals(user1.getUsername(),loadUserByUsername2.getUsername());
	}
	@Test
	 void loadUserByUsernameTestFail() {
		
		Optional<Userdata> data =Optional.ofNullable(null) ;
		when(userservice.findById("kumar")).thenReturn(data);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("kumar"));
	}
	@Test
	void userNotFound() {
		
		when(userservice.findById("kumar")).thenReturn(null);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("kumar"));
	}
}
