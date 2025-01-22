package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SigninDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.jwt.JwtProvider;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private static AuthService authService;
    private JwtProvider jwtProvider;
    private AuthDao authDao;

    private AuthService() {
        authDao = AuthDao.getInstance();
        jwtProvider = JwtProvider.getInstance();
    }
    public static AuthService getInstance() {
        if (authService == null) {
            authService = new AuthService();
        }
        return authService;
    }

    public ResponseDto<?> signup(SignupDto signupDto) {
        User user = signupDto.toUser();
        User insertUser = authDao.signup(user);
        if(insertUser == null) {
            return ResponseDto.fail("Signup failed");
        }
        return ResponseDto.success(insertUser);
    }

    public ResponseDto<?> signin(SigninDto signinDto) {
        User foundUser = authDao.findUserByUsername(signinDto.getUsername());
        if(foundUser == null) {
            return ResponseDto.fail("Signin failed");
        }
        if(!BCrypt.checkpw(signinDto.getPassword(), foundUser.getPassword())) {
            return ResponseDto.fail("Signin failed");
        }
        return ResponseDto.success(jwtProvider.generateToken(foundUser));
    }
}
