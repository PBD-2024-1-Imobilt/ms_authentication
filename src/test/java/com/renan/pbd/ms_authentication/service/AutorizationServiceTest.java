package com.renan.pbd.ms_authentication.service;

import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.model.UserModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class AutorizationServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AutorizationService autorizationService;

    private UserModel createUser(UserRequestDto userRequestDto){
        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(userRequestDto, userModel);

        this.entityManager.persist(userModel);
        return  userModel;
    }

    @Test
    @DisplayName("Load user: Case of sucess!")
    @Transactional
    void loadUserByUsernameSucessTest(){
        var user = createUser(new UserRequestDto("renan", "123"));
        UserDetails userSearch = autorizationService.loadUserByUsername(user.getUsername());
        Assertions.assertNotNull(userSearch);
    }

    @Test
    @DisplayName("Load user: Case of error")
    void loadUserByUsernameErrorTest(){
        try{
            String username = "renan";
            autorizationService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            Assertions.assertEquals("User not found !", e.getMessage());
        }
    }



}