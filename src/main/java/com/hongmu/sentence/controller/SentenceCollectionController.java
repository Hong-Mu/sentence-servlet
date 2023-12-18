package com.hongmu.sentence.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.hongmu.sentence.dao.CollectionDao;
import com.hongmu.sentence.dao.SentenceDao;
import com.hongmu.sentence.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sc")
public class SentenceCollectionController extends HttpServlet {

    private SentenceDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new SentenceDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int sentenceId = Integer.parseInt(request.getParameter("sentenceId"));
        int collectionId = Integer.parseInt(request.getParameter("collectionId"));

        int result = dao.setCollection(sentenceId, collectionId);
        if (result == 1) {
            response.sendRedirect("collection.jsp?id=" + collectionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
