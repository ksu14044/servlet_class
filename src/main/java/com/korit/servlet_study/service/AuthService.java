package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;

public class AuthService {
    private static AuthService authService;

    private AuthDao authDao;

    private AuthService() {
        authDao = AuthDao.getInstance();
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
}
