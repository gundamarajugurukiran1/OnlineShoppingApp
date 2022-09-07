package com.shoppingapp.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingapp.userservice.model.Admindata;


@Repository
public interface AdminRepository extends JpaRepository<Admindata, String> {

	public Optional<Admindata> findById(String uname);
}
