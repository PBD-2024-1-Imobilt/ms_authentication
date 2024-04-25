package com.renan.pbd.ms_authentication.exceptions;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;


public class ExceptiosHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticateException.class)
    public ProblemDetail authenticateException(AuthenticateException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/validation"
                )
        );
        return  problemDetail;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail userNotFoundException(UserNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/validation"
                )
        );
        return  problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialsException (BadCredentialsException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/token"
                )
        );
        return  problemDetail;
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ProblemDetail signatureVerificationException(SignatureVerificationException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/validation"
                )
        );
        return  problemDetail;
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail usernameNotFoundException(UsernameNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/validation"
                )
        );
        return  problemDetail;
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ProblemDetail jwtDecodeException(JWTDecodeException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(
                URI.create(
                        "http://localhost:8080//api/vi/authetication/validation"
                )
        );
        return  problemDetail;
    }


}
