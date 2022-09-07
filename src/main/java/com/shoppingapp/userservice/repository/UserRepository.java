package com.shoppingapp.userservice.repository;


import com.shoppingapp.userservice.model.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userdata, String> {

}
