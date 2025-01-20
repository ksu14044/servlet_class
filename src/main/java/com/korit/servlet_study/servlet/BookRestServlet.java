package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Author author = new Author(1, "testAuthor");

        Publisher publisher = new Publisher(1, "testPublisher");

        BookCategory bookCategory = new BookCategory(1, "testBookCategory");

        Book book = Book.builder()
                .bookId(1)
                .bookName("Test Book")
                .authorId(author.getAuthorId())
                .author(author)
                .isbn("123")
                .publisher(publisher)
                .category(bookCategory)
                .bookImgUrl("https://www.test.com")
                .publisherId(publisher.getPublisherId())
                .build();

//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");

        String json = objectMapper.writeValueAsString(book);
        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
