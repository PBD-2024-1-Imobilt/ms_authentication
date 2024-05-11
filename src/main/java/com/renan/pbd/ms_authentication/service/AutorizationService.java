package com.renan.pbd.ms_authentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renan.pbd.ms_authentication.repository.UserRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AutorizationService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserDetails> user = userRepository.findByUsername(name);
        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("User not found !");
    }
}
