package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.server_flow.Response;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = User.builder()
                .username("test")
                .password("1234")
                .name("테스트")
                .email("test@email.com")
                .build();

        String jsonUser = objectMapper.writeValueAsString(user);
        System.out.println(jsonUser);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setContentType("application/json");
        resp.getWriter().println(jsonUser);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
