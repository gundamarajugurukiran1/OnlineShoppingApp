package com.shoppingapp.userservice.serviceImpl;
import java.util.ArrayList;
import java.util.Optional;

import com.shoppingapp.userservice.Exception.UnauthorizedException;
import com.shoppingapp.userservice.model.Admindata;
import com.shoppingapp.userservice.model.Userdata;
import com.shoppingapp.userservice.repository.AdminRepository;
import com.shoppingapp.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**Service class*/
@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userdao;
    @Autowired
    private AdminRepository admindao;
    @Override
    public UserDetails loadUserByUsername(String uname) {

        try
        {
            log.info("loading the user");
            Optional<Userdata> user=userdao.findById(uname);
            if(user.isPresent()) {
                return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
            }
            else {
                log.info("user not found");
                throw new UsernameNotFoundException("User id not found");
            }
        }
        catch (Exception e) {
            log.info("exception occured");
            throw new UnauthorizedException("Username Not Found Exception");
        }


    }

    public UserDetails loadAdminByUsername(String uname) {

        try
        {
            log.info("loading the admin");
            Optional<Admindata> admin =admindao.findById(uname);
            if(admin.isPresent()) {
                return new User(admin.get().getUsername(), admin.get().getPassword(), new ArrayList<>());
            }
            else {
               log.info("admin not found");
                throw new UsernameNotFoundException("admin id not found");
            }
        }
        catch (Exception e) {
            log.info("exception occured");
            throw new UnauthorizedException("Username Not Found Exception");
        }


    }

}

