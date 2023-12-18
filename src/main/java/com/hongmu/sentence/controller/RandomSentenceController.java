
package com.hongmu.sentence.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongmu.sentence.dao.SentenceDao;
import com.hongmu.sentence.model.Sentence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/random")
public class RandomSentenceController extends HttpServlet {

    private SentenceDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new SentenceDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Sentence sentence = dao.getRandom();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(sentence);

        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
