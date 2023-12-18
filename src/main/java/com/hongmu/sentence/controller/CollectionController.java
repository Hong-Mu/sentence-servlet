package com.hongmu.sentence.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.hongmu.sentence.dao.CollectionDao;
import com.hongmu.sentence.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/collection")
public class CollectionController extends HttpServlet {

    private CollectionDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new CollectionDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("name");
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        int result = dao.insert(title, userId);
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
