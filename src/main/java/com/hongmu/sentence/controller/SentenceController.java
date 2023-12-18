package com.hongmu.sentence.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.hongmu.sentence.dao.SentenceDao;
import com.hongmu.sentence.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sentence")
public class SentenceController extends HttpServlet {

    private SentenceDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new SentenceDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("sentence");
        String subtitle = request.getParameter("author");
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        int result = dao.insert(title, subtitle, userId);
        if (result == 1) {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
