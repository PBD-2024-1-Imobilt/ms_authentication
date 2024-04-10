package com.renan.pbd.ms_authentication.controller;

import java.util.Collections;
import java.util.Map;

import org.hibernate.mapping.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renan.pbd.ms_authentication.dto.TokenRequestResponseDto;
import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.model.UserModel;
import com.renan.pbd.ms_authentication.service.TokenService;
import com.renan.pbd.ms_authentication.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/vi/authentication")
@AllArgsConstructor
public class AutheticationController {

    private final UserService userService;

    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<TokenRequestResponseDto> postCreateToken(@RequestBody @Valid UserRequestDto userDate){

        var authUser = userService.authenticateUserService(userDate);

        var token = tokenService.generateToken((UserModel) authUser.getPrincipal());

        return ResponseEntity.ok(new TokenRequestResponseDto(token));
    }

    @PostMapping("/validation")
    public ResponseEntity<?> postValidateToken(
            @RequestBody @Valid TokenRequestResponseDto tokenReq){
        if (tokenReq.token() != null){
            var username = tokenService.valueDateToken(tokenReq.token());

            UserDetails user = userService.findByNameService(username);

            if (user != null)
                return ResponseEntity.ok(Collections.emptyMap());
            
                
            throw new ArithmeticException("Não existe usuário cadastrado com esse token");
        }
        throw new ArithmeticException("O token está nulo !");

    }
}
