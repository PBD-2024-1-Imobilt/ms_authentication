package com.renan.pbd.ms_authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public Authentication authenticateUserService(UserRequestDto userDate) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(
                userDate.username(), userDate.password());

        return authenticationManager.authenticate(userNamePassword);
    }

    public UserDetails findByNameService(String name) {
        return userRepository.findByUsername(name);
    }

}
