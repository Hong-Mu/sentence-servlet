package com.hongmu.sentence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hongmu.sentence.util.JDBCUtil;

import jakarta.annotation.Resource;

public class UserDao {

    private static String INSERT_USERS_SQL = "INSERT INTO users"
            + "  (email, username, password) VALUES "
            + " (?, ?, ?);";

    public int register(String email, String username, String password) {

        int result = 0;

        try (Connection connection = JDBCUtil.getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate(); // 반영된 레코드 수 반환

        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }

}
