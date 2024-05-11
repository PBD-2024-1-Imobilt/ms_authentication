package com.renan.pbd.ms_authentication.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.model.UserModel;
import com.renan.pbd.ms_authentication.repository.UserRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    private UserModel createUser(UserRequestDto userRequestDto){
        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(userRequestDto, userModel);

        this.entityManager.persist(userModel);
        return  userModel;
    }

    private String createToken(){
        UserModel user = createUser(new UserRequestDto("Renan", "123"));
        return tokenService.generateToken(user);
    }

    @Test
    @DisplayName("Generate token: case of sucess")
    @Transactional
    void generateTokenSucess() {
        String token = createToken();
        Assertions.assertNotNull(token);
        Assertions.assertNotEquals("", token);
    }

    @Test
    @DisplayName("Generate token: case of error")
    void generateTokenError() {
        try{
            UserModel user = new UserModel();
            BeanUtils.copyProperties(new UserRequestDto("Renan", "123"), user);
            tokenService.generateToken(user);
        } catch(JWTCreationException e){
            Assertions.assertEquals("Error while generate token", e.getMessage());
        }
    }

    @Test
    @Transactional
    @DisplayName("Validation Token: case of sucess")
    void valueDateTokenSucess() {
       String token = createToken();
       String username = tokenService.valueDateToken(token);
       Optional<UserDetails> user = userRepository.findByUsername(username);
       Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("Validation Token: cases of error")
    void valueDateTokenError(){
        try{
            tokenService.valueDateToken("null");
        }catch(SignatureVerificationException e){
            Assertions.assertEquals("The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256", e.getMessage());
        }catch(JWTDecodeException e2){
            Assertions.assertEquals("The token was expected to have 3 parts, but got 0.", e2.getMessage());
            
        }

    }
}