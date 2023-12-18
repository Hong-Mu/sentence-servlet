package com.hongmu.sentence.controller.auth;

import java.io.IOException;

import com.hongmu.sentence.dao.LoginDao;
import com.hongmu.sentence.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private LoginDao dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = dao.validate(email, password);
        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // 로그인 실패하면 같은 페이지로 포워딩
            request.setAttribute("message", "Login Fail");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
