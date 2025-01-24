package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.server_flow.Response;
import com.korit.servlet_study.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {
    private UserService userService;

    public UserRestServlet() {
        userService = UserService.getInstance();
    }
    @JwtValid
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userIdParam = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        ResponseDto<?> responseDto = userService.getUser(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(responseDto);
        System.out.println(jsonUser);

//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json");
        response.getWriter().println(jsonUser);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
