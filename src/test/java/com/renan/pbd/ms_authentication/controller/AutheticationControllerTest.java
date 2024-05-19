package com.renan.pbd.ms_authentication.controller;

import com.renan.pbd.ms_authentication.dto.TokenRequestResponseDto;
import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.model.UserModel;
import com.renan.pbd.ms_authentication.service.TokenService;
import com.renan.pbd.ms_authentication.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AutheticationControllerTest {
    private UserRequestDto userDate;
    private UserModel user;
    private String username, token;
    private Optional<UserDetails> optionalUser;
    @InjectMocks
    private AutheticationController autheticationController;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void startUp() {
        MockitoAnnotations.openMocks(this);
        userDate = new UserRequestDto("teste", "123");
        user = new UserModel();
        BeanUtils.copyProperties(userDate, user);
        username = user.getUsername();
        optionalUser = Optional.of(user);
        token = "teste646554654646.5464654.564465";
        autheticationController = new AutheticationController(userService, tokenService);
    }

    @Test
    @DisplayName("Create Token Test")
    void createTokenTest() {
        when(userService.authenticateUserService(userDate)).thenReturn(authentication);
        when((UserModel) userService.authenticateUserService(userDate).getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user)).thenReturn(anyString());
        ResponseEntity<TokenRequestResponseDto> createToken = autheticationController.postCreateToken(userDate);
        verify(userService, times(2)).authenticateUserService(userDate);
        verify(tokenService, times(1)).generateToken(user);
        Assertions.assertEquals(createToken, ResponseEntity.ok(new TokenRequestResponseDto(anyString())));
    }

    @Test
    @DisplayName("Validate token test sucess")
    void validateTokenSucess() {
        when(tokenService.valueDateToken(anyString())).thenReturn(username);
        when(userService.findByNameService(username)).thenReturn(optionalUser);
        ResponseEntity<Object> validateToken = autheticationController.postValidateToken(new TokenRequestResponseDto(token));
        verify(tokenService, times(1)).valueDateToken(anyString());
        verify(userService, times(1)).findByNameService(username);
        Assertions.assertEquals(validateToken, ResponseEntity.ok(Collections.emptyMap()));
    }

    @Test
    @DisplayName("Validate Token error")
    void validateTokenError(){
        ArithmeticException thrown = Assertions.assertThrows(
                ArithmeticException.class,
                () -> autheticationController.postValidateToken(new TokenRequestResponseDto(eq(null)))
        );
        Assertions.assertEquals("O token está nulo !", thrown.getMessage());

        ArithmeticException thrown2 = Assertions.assertThrows(
                ArithmeticException.class,
                () -> autheticationController.postValidateToken(new TokenRequestResponseDto(token))
        );
        Assertions.assertEquals("Não existe usuário cadastrado com esse token", thrown2.getMessage());
    }


}