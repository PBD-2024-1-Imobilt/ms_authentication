package com.renan.pbd.ms_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

import com.renan.pbd.ms_authentication.model.UserModel;
/*Respositorio*/
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserDetails findByUsername(String username);
}

