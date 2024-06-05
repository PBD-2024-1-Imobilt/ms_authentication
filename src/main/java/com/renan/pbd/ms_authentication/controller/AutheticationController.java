package com.renan.pbd.ms_authentication.controller;

import com.renan.pbd.ms_authentication.dto.TokenRequestResponseDto;
import com.renan.pbd.ms_authentication.dto.UserRequestDto;
import com.renan.pbd.ms_authentication.model.UserModel;
import com.renan.pbd.ms_authentication.service.TokenService;
import com.renan.pbd.ms_authentication.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/vi/authentication")
public class AutheticationController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private  TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<TokenRequestResponseDto> postCreateToken(@RequestBody @Valid UserRequestDto userDate){

        UserModel authUser = (UserModel) userService.authenticateUserService(userDate).getPrincipal();

        String token = tokenService.generateToken(authUser);

        return ResponseEntity.ok(new TokenRequestResponseDto(token));
    }

    @PostMapping("/validation")
    public ResponseEntity<Object> postValidateToken(
            @RequestBody @Valid TokenRequestResponseDto tokenReq){
        if (tokenReq.token() != null){
            var username = tokenService.valueDateToken(tokenReq.token());

            Optional<UserDetails> user = userService.findByNameService(username);

            if (user.isPresent())
                return ResponseEntity.ok(Collections.emptyMap());
            
                
            throw new ArithmeticException("Não existe usuário cadastrado com esse token");
        }
        throw new ArithmeticException("O token está nulo !");

    }
}
