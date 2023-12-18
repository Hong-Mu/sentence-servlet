package com.hongmu.sentence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hongmu.sentence.model.User;
import com.hongmu.sentence.util.JDBCUtil;

public class LoginDao {

    private static String QUERY_USER_SQL = "select * from users where email = ? and password = ?";

    public User validate(String email, String password) {
        User user = null;
        try (Connection connection = JDBCUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_USER_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery(); // 쿼리 실행
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"));
            }

        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return user;
    }
}
